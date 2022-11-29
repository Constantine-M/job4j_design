package ru.job4j.ood.lsp.storegoods.store;

import ru.job4j.ood.lsp.storegoods.control.ControlQuality;
import ru.job4j.ood.lsp.storegoods.food.Food;

import java.util.List;
import java.util.function.Predicate;

/**
 * Данный класс описывает,
 * грубо говоря, мусорный бак.
 *
 * Если срок годности товара вышел,
 * то он попадает сюда.
 */
public class Trash extends AbstractStore {

    private ControlQuality controlQuality;

    @Override
    public Food add(Food food) {
        return super.add(food);
    }

    @Override
    public boolean delete(Food food) {
        return super.delete(food);
    }

    @Override
    public List<Food> findAll(Predicate<Food> filter) {
        return super.findAll(filter);
    }
}
