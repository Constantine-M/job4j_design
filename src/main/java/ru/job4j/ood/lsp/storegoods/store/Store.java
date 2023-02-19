package ru.job4j.ood.lsp.storegoods.store;

import ru.job4j.ood.lsp.storegoods.food.Food;

import java.util.List;
import java.util.function.Predicate;

/**
 * Данный интерфейс описывает
 * хранилище продуктов, а также
 * основные методы взаимодействия
 * с продуктами.
 */
public interface Store {

    boolean add(Food food);

    boolean delete(Food food);

    List<Food> findAll(Predicate<Food> predicate);

    void clear();
}
