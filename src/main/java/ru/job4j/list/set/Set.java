package ru.job4j.list.set;

/**
 * 1. Реализовать коллекцию Set на массиве.
 *
 * Данный интерфейс расширяет интерфейс
 * Collection и представляет
 * набор уникальных элементов.
 *
 * @author Constantine on 14.11.2021
 */
public interface Set<T> extends Iterable<T> {

    boolean add(T value);
    boolean contains(T value);
}
