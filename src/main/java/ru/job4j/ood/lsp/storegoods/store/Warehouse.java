package ru.job4j.ood.lsp.storegoods.store;


import ru.job4j.ood.lsp.storegoods.control.ExpirationCalculator;
import ru.job4j.ood.lsp.storegoods.food.Food;

import java.util.Calendar;

/**
 * Данный класс описывает
 * товарное хранилище - место,
 * откуда все попадает в магазин.
 *
 * Здесь хранятся продукты если срок
 * годности израсходован меньше чем на 25%.
 */
public class Warehouse extends AbstractStore {

    private static final byte EXPIRATION_PROGRESS = 25;

    private final ExpirationCalculator<Calendar> expCalculator;

    public Warehouse(ExpirationCalculator<Calendar> expCalculator) {
        this.expCalculator = expCalculator;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        return expCalculator.calculateInPercent(
                food.getCreateDate(), food.getExpiryDate()) < EXPIRATION_PROGRESS;
    }
}
