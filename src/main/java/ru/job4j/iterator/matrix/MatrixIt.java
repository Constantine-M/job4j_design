package ru.job4j.iterator.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.1. Итератор для двухмерного массива int[][].
 */
public class MatrixIt implements Iterator {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Данный метод проверяет
     * наличие следующего элемента.
     * 1.Проверяем, что нам не приходит
     * массив длиной = 0.
     * 2.Проверяем, что ячейка не является
     * последней в строке. Если является,
     * то переходим на новую строку.
     * Иначе мы бы возвращали пустую
     * строку (1 - пусто - пусто - 2).
     * 3.Проверяем, что строка не пустая.
     * Пустую пропускаем.
     * @return true если далее есть
     * элемент массива.
     */
    @Override
    public boolean hasNext() {
        while (data.length != 0 && row <= data.length - 1 && data[row].length == column) {
            row++;
            column = 0;
        }
        return row < data.length && column < data[row].length;
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
        return data[row][column++];
    }
}
