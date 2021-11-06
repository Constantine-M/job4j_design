package ru.job4j.list.linked;

/**
 * 2. Создать контейнер на базе связанного списка.
 *
 * @author Constantine on 04.11.2021
 */
public interface List<E> extends Iterable<E> {

    void add(E value);

    E get(int index);

    E deleteFirst();
}
