package ru.job4j.ood.lsp.storegoods.store;


import ru.job4j.ood.lsp.storegoods.control.ExpirationCalculator;
import ru.job4j.ood.lsp.storegoods.food.Food;

import java.util.Calendar;

/**
 * Данный класс описывает
 * магазин.
 * Здесь хранятся продукты с сроком
 * годности от 25% до 75%.
 */
public class Shop extends AbstractStore {

    private static final byte EXPIRATION_PROGRESS_LOW_LIMIT = 25;

    private static final byte EXPIRATION_PROGRESS_HIGH_LIMIT = 75;

    /**
     * Скидка в процентах. Вынес в отдельное
     * поле, чтобы при изменении скидки
     * не рефакторить методы, а только
     * значение поля изменить.
     */
    private static final double DISCOUNT = 30;

    private final ExpirationCalculator<Calendar> expCalculator;

    public Shop(ExpirationCalculator<Calendar> expCalculator) {
        this.expCalculator = expCalculator;
    }

    /**
     * Хотел использовать здесь
     * модный switch-expressions,
     * но он не подошел.
     */
    @Override
    protected boolean isNotExpired(Food food) {
        boolean result = false;
        double curExpProgress = expCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        if (curExpProgress >= EXPIRATION_PROGRESS_LOW_LIMIT
            && curExpProgress <= EXPIRATION_PROGRESS_HIGH_LIMIT) {
            result = true;
        } else if (curExpProgress >= EXPIRATION_PROGRESS_HIGH_LIMIT && curExpProgress != 100) {
            setDiscountedPrice(food);
            result = true;
        }
        return result;
    }

    private void setDiscountedPrice(Food food) {
        food.setDiscount(DISCOUNT);
        double newPrice = food.getPrice() - (food.getPrice() * (DISCOUNT / 100));
        food.setPrice(newPrice);
    }
}
