package ru.job4j.ood.lsp.storegoods.food;

import java.util.Calendar;

public class Eggs extends  Food {

    public Eggs(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
