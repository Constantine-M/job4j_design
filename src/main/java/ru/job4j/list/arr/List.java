package ru.job4j.list.arr;

/**
 * 1. Динамический список на массиве.
 *
 * Данный интерфейс описывает работу
 * списка с помощью нескольких методов.
 * Интерфейс принимает любой объект.
 * @author Constantine on 31.10.2021
 */
public interface List<T> extends Iterable<T> {

    void add(T value);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();
}
