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
     * Данный метод добавляет продукт
     * в {@link Shop}. Мы его переопределили,
     * т.к. добавили в него начисление
     * скидки.
     * Здесь есть интересная конструкция,
     * проверяющая, добавился продукт
     * или нет. Спасибо, учитель!
     *
     * @param food продукт.
     * @return true, если продукт был
     * добавлен в {@link Shop}.
     */
    @Override
    public boolean add(Food food) {
        boolean isAdded = super.add(food);
        if (!isAdded) {
            return false;
        }
        if (needToDiscount(food)) {
            setDiscountedPrice(food);
        }
        return true;
    }

    /**
     * Данный метод проверяет,
     * просрочен продукт или нет.
     * Результат в %. Если срок годности
     * в % от 25% до 75%, то
     * продукт отправится в магазин.
     * Здесь мы вместо 75% сравнили
     * с 100, т.к. диапазон
     * 75-99 никак не классифицируется.
     * Он нужен для начисления скидки.
     * В любом случае продукт попадет
     * в магазин.
     */
    @Override
    protected boolean isNotExpired(Food food) {
        double curExpProgress = expCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return curExpProgress >= EXPIRATION_PROGRESS_LOW_LIMIT && curExpProgress < 100;
    }

    /**
     * Данный метод проверяет,
     * нужно ли делать скидку на продукт.
     * Если срок годности в % больше 75%,
     * то нужна скидка. Также нужно сравнить
     * с 100, иначе просрочке тоже сделают
     * скидку и отправят в магазин.
     * @param food продукт.
     * @return true, если нужна скидка.
     */
    private boolean needToDiscount(Food food) {
        double curExpProgress = expCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return curExpProgress >= EXPIRATION_PROGRESS_HIGH_LIMIT && curExpProgress < 100;
    }

    private void setDiscountedPrice(Food food) {
        food.setDiscount(DISCOUNT);
        double newPrice = food.getPrice() - (food.getPrice() * (DISCOUNT / 100));
        food.setPrice(newPrice);
    }
}
