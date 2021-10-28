package ru.job4j.generics.containers;

/**
 * 5.2.2. Реализовать Store<T extends Base>.
 *
 * Данный класс описывает базовую модель.
 * Т.е. объект класса Base - это
 * все что угодно в хранилище. И у него
 * имеется свой id.
 * @author Constantine on 27.10.2021
 */
public abstract class Base {
    private final String id;

    public Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
