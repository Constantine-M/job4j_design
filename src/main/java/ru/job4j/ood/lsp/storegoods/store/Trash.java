package ru.job4j.ood.lsp.storegoods.store;


import ru.job4j.ood.lsp.storegoods.control.ExpirationCalculator;
import ru.job4j.ood.lsp.storegoods.food.Food;

import java.util.Calendar;

/**
 * Данный класс описывает,
 * грубо говоря, мусорный бак.
 *
 * Если срок годности товара вышел,
 * то он попадает сюда.
 */
public class Trash extends AbstractStore {

    private static final byte EXPIRATION_PROGRESS = 100;

    private final ExpirationCalculator<Calendar> expCalculator;

    public Trash(ExpirationCalculator<Calendar> expCalculator) {
        this.expCalculator = expCalculator;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        return expCalculator.calculateInPercent(
                food.getCreateDate(), food.getExpiryDate()) >= EXPIRATION_PROGRESS;
    }
}
