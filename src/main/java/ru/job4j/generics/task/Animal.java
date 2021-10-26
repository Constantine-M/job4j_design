package ru.job4j.generics.task;

/**
 * 0. Что такое обобщенные типы (generics).
 * @author Constantine on 26.10.2021
 */
public class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}
