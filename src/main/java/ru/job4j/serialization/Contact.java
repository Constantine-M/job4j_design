package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

/**
 * 1. Что такое Сериализация?
 *
 * Данный класс описывает процесс
 * сериализации и принцип работы
 * данного механизма.
 *
 * Сериализация – процесс преобразования
 * объектов в бинарный или текстовый
 * формат.
 * Десериализация - обратный процесс.
 *
 * Данный механизм используется для
 * сохранения состояния программы
 * между запусками, хранения настроек,
 * передачи данных между программами
 * локально или по сети.
 *
 * В Java существует стандартный
 * механизм сериализации в бинарный
 * формат – Java serialization, из
 * текстовых форматов наиболее
 * популярны JSON, XML, YAML,
 * BSON (binary JSON).
 *
 * Для сериализации объектов в
 * поток используется метод
 * {@link ObjectOutputStream#writeObject(Object)}.
 * Для чтения из потока
 * {@link ObjectInputStream#readObject()}.
 *
 * @author Constantine on 12.03.2022
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream(tempFile)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
