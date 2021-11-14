package ru.job4j.list.set;

import ru.job4j.list.arr.SimpleArrayList;

import java.util.Iterator;

/**
 * 1. Реализовать коллекцию Set на массиве.
 *
 * Данный класс описывает особенности
 * интерфейса {@link Set}.
 * Для хранения будем использовать
 * SimpleArrayList.
 *
 * @author Constantine on 14.11.2021
 */
public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(1);

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        Iterator<T> it = set.iterator();
        if (value == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    rsl = true;
                    break;
                }
            }
        } else {
            while (it.hasNext()) {
                if (value.equals(it.next())) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    /**
     * Мы используем итератор класса
     * {@link SimpleArrayList}.
     */
    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
