package ru.job4j.generics.containers;

/**
 * 5.2.2. Реализовать Store<T extends Base>.
 *
 * Данный интерфейс описывает все контейнеры.
 *
 * Данный интерфейс принимает
 * какой-то объект (модель) класса
 * {@link Base} или его наследника.
 * @author Constantine on 27.10.2021
 */
public interface Store<T extends Base> {

    void add(T model);

    void replace(String id, T model);

    void delete(String id);

    T findById(String id);
}
