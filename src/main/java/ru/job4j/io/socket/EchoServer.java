package ru.job4j.io.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 2. Что такое Socket?
 *
 * Данный класс описывает сервер и
 * принципы его работы.
 *
 * 1.Сначала создаем сервер.
 * Вызов конструктора {@link ServerSocket}
 * создает серверный сокет, привязанный к
 * указанному порту. Чтобы клиент мог
 * узнать, где находится сервер ему
 * нужен адрес и порт, 9000 - это порт.
 * По умолчанию адрес будет = localhost.
 *
 * 2.Условие в цикле говорит о том,
 * что сервер работает, пока его
 * принудительно не закроют.
 *
 * 3.Далее создаем клиентский сокет.
 * Вызов метода accept() заставляет
 * программу ждать подключений по
 * указанному порту, работа программы
 * продолжится только после подключения
 * клиента. После успешного подключения
 * метод возвращает объект Socket,
 * который используется для
 * взаимодействия с клиентом.
 *
 * Метод {@link ServerSocket#accept()}
 * принимает один запрос от клиента.
 * Чтобы отправить второй, программа
 * должна снова получить объект {@link Socket}.
 *
 * 4.Во втором блоке try с помощью
 * объекта {@link Socket} программа
 * может получить входной поток и может
 * отправить данные в выходной поток.
 *
 * 5.В ответ мы записываем строчку,
 * где указали, что все прочитали:
 * HTTP/1.1 200 OK\r\n\r\n
 *
 * 6.В программе читается весь
 * входной поток через цикл for.
 *
 * 7.После чтения отправляем ответ
 * окончательно.
 *
 * ДОРАБОТАНО
 *
 * 1.Входящий поток читаем с помощью
 * {@link Scanner}.
 *
 * 2.Если входящая строка = null
 * или пустая - выходим из цикла.
 *
 * 3.Внутри цикла проверяем что
 * в строке содержится сообщение
 * с текстом "Bye".
 * Если нашли, то закрываем сокет,
 * тем самым разрывая соединение
 * между сервером и клиентом.
 *
 * Мы успеваем посмотреть всю
 * информацию, принятую от сервера,
 * отреагировать на нее и закрыть
 * соединение.
 *
 * P.S. To check out your service,
 * enter <http://localhost:9000/?msg=Hello>
 * in your browser.
 * Better use the Internet Explorer
 * browser!
 *
 * @author Constantine on 27.02.2022
 */
public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                System.out.println("Server is waiting for connection..");
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                Scanner scan = new Scanner(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = scan.nextLine();
                    String[] splitStr = str.split("\\s");
                    while (str != null && !str.isEmpty()) {
                        if (str.contains("/?msg=")) {
                            if (splitStr[1].equals("/?msg=Exit")) {
                                server.close();
                            } else if (splitStr[1].equals("/?msg=Hello")) {
                                out.write("Hey man! Whats shakin?".getBytes());
                            } else {
                                out.write("What".getBytes());
                            }
                        }
                        System.out.println(str);
                        str = scan.nextLine();
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
