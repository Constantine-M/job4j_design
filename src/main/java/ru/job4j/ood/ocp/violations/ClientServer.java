package ru.job4j.ood.ocp.violations;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сделаю оговорку. Возможно, что
 * у меня не получилось показать
 * зависимость клиента от конкретного
 * сервера, но попытался показать
 * именно такой случай.
 *
 * Так вот в подобном случае, когда
 * клиенту необходимо работать с
 * разными серверами, то здесь мы
 * расширить программу уже не можем.
 * Это пример нарушения принципа OCP.
 *
 * Для решения проблемы нужно было
 * заранее создать прослойку между
 * клиентом и сервером - так называемый
 * абстрактный сервер. То есть нужно
 * добавить интерфейс AbstractServer и через
 * него работать с любым сервером.
 */
public class ClientServer {

    private static class Client {
        public void run() {
            try {
                Socket socket = new Socket("localhost", 888);
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF("Hello Server");
                dout.flush();
                dout.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class Server {
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(888);
                Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String str = dis.readUTF();
                System.out.println("message= " + str);
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        Server server = new Server();
        client.run();
        server.run();
    }
}
