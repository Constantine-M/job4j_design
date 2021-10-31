package ru.job4j.list.ownlist;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 1. Динамический список на массиве.
 * @author Constantine on 31.10.2021
 */
public class SimpleArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * Данное поле описывает массив,
     * на основе которого будет
     * создаваться список.
     */
    private T[] container;

    /**
     * Данное поле описывает
     * количество элементов в списке.
     */
    private int size;

    /**
     * Данное поле описывает счетчик,
     * который будет увеличиваться
     * после модификации списка.
     */
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {

    }

    @Override
    public T set(int index, T newValue) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    /**
     * Данный метод увеличивает емкость
     * массива, чтобы в дальнейшем
     * можно было хранить по крайне мере
     * кол-во элементов = минимальной
     * емкости.
     *
     * Наше временное хранилище парметризовано
     * дженериком - не получается создать
     * новый массив.
     *
     * @param minCapacity минимальная емкость.
     * @return новый массив с увеличенной емкостью.

    private T[] grow(int minCapacity) {
        int oldCapacity = container.length;
        if (oldCapacity > 0 || container != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = oldCapacity * 2;
            return container = Arrays.copyOf(container, newCapacity);
        } else {
            return container = new T[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }
    */
}
