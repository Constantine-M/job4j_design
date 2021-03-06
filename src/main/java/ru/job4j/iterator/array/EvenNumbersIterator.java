package ru.job4j.iterator.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.2. Создать итератор четные числа.
 * @author Constantine on 02.10.2021
 */
public class EvenNumbersIterator implements Iterator {
    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Данный метод проверяет
     * наличие следующего элемента,
     * удовлетворяющего условиям.
     * 1.Проверяем, что длина массива != 0.
     * 2.Проверяем, что элемент не последний
     * в массиве и является четным.
     * Если последний или если нечетный,
     * то пропускаем.
     * @return true, если далее имеется
     * четное число.
     */
    @Override
    public boolean hasNext() {
        while (data.length != index && data[index] % 2 != 0) {
            index++;
        }
        return index < data.length && data[index] % 2 == 0;
    }

    /**
     * Данный метод возвращает четное число.
     * Является указателем, который мы передвигаем
     * от элемента к элементу.
     * @return четное число.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
