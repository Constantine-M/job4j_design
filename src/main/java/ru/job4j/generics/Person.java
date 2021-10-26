package ru.job4j.generics;

import java.util.Date;

/**
 * 0. Что такое обобщенные типы (generics).
 * Данный класс создан в учебных целях
 * для более подробного рассмотрения
 * обобщенных типов.
 * @author Constantine on 25.10.2021
 */
public class Person {
    private String name;
    private int age;
    private Date birthday;

    public Person(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", birthday=" + birthday
                + '}';
    }
}
