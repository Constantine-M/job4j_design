package ru.job4j.generics;

import java.util.Date;

/**
 0. Что такое обобщенные типы (generics).
 * Данный класс создан в учебных целях
 * для более подробного рассмотрения
 * обобщенных типов.
 * @author Constantine on 25.10.2021
 */
public class Programmer extends Person {
    public Programmer(String name, int age, Date birthday) {
        super(name, age, birthday);
    }
}
