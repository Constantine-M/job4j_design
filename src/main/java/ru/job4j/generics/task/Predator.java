package ru.job4j.generics.task;

/**
 * 0. Что такое обобщенные типы (generics).
 * @author Constantine on 26.10.2021
 */
public class Predator extends Animal {
    private int size;

    public Predator(String name, int age, int size) {
        super(name, age);
        this.size = size;
    }
}
