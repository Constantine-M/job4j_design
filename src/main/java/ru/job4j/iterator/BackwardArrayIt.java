package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1. Что такое итератор.
 * Данный класс описывает принцип
 * работы итератора. В данном случае
 * обход элементов происходит с конца
 * одномерного массива.
 */
public class BackwardArrayIt implements Iterator {
    private final int[]  data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Данный метод возвращает
     * элемент массива с конца.
     * Если массив пустой или
     * далее ничего нет, то выбросит
     * {@link NoSuchElementException}.
     * @return элемент с конца.
     */
    @Override
    public Integer next() {
        int lastEl = data.length - 1;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[lastEl - point++];
    }
}
