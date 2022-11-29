package ru.job4j.ood.lsp.storegoods.food;

import java.util.Calendar;

/**
 * 1. Хранилище продуктов.
 * Данный класс описывает модель продукта.
 * Он же явлется родительским -
 * на его основе реализованы остальные
 * продукты.
 */
public class Food {

    private String name;

    private Calendar expiryDate;

    private Calendar createDate;

    private double price;

    private double discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
