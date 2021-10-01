package ru.job4j.iterator.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.1. Итератор для двухмерного массива int[][].
 */
public class MatrixIt implements Iterator {
    private final int[][] data;
    private int i = 0;
    private int j = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Данный метод проверяет
     * наличие следующего элемента.
     * 1.Проверяем, что ячейка не является
     * последней в строке. Если является,
     * то переходим на новую строку.
     * Иначе мы бы возвращали пустую
     * строку (1 - пусто - пусто - 2).
     * 2.Проверяем, что строка не пустая.
     * Пустую пропускаем.
     * @return true если далее есть элемент массива.
     */
    @Override
    public boolean hasNext() {
        if (j == data[i].length) {
            i++;
            j = 0;
        }
        while (i <= data.length - 1 && data[i].length == 0) {
            i++;
        }
        return i < data.length && j <= data[i].length;
    }

    /**
     * Данный метод переводит
     * "указатель" на следующий
     * элемент.
     * @return значение ячейки массива.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[i][j++];
    }
}
