package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1. Что такое итератор.
 * Данный класс описывает описывает
 * принцип работы итератора на примере
 * одномерного массива.
 */
public class ArrayIt implements Iterator {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Данные метод показывает, есть
     * ли в массиве еще ячейки.
     * Поле {@code point} в нашем случае
     * будет служить указателем при
     * прохождении по массиву.
     * @return true or false.
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Данный метод возвращает элемент
     * массива и увеличивает указатель на 1.
     * @return next element.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
