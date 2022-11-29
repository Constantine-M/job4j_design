package ru.job4j.ood.lsp.storegoods.food;

import java.util.Calendar;

public class Eggs extends  Food {

    public Eggs(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Calendar getExpiryDate() {
        return super.getExpiryDate();
    }

    @Override
    public void setExpiryDate(Calendar expiryDate) {
        super.setExpiryDate(expiryDate);
    }

    @Override
    public Calendar getCreateDate() {
        return super.getCreateDate();
    }

    @Override
    public void setCreateDate(Calendar createDate) {
        super.setCreateDate(createDate);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public double getDiscount() {
        return super.getDiscount();
    }

    @Override
    public void setDiscount(double discount) {
        super.setDiscount(discount);
    }
}
