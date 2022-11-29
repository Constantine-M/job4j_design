package ru.job4j.ood.lsp.storegoods.store;

import ru.job4j.ood.lsp.storegoods.control.ControlQuality;
import ru.job4j.ood.lsp.storegoods.food.Food;

import java.util.List;
import java.util.function.Predicate;

/**
 * Данный класс описывает
 * товарное хранилище - место,
 * откуда все попадает в магазин.
 *
 * Здесь хранятся продукты если срок
 * годности израсходован меньше чем на 25%.
 */
public class Warehouse extends AbstractStore {

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
