package ru.job4j.ood.lsp.storegoods.food;

import java.util.Calendar;

public class FrozenBeef extends Food {

    public FrozenBeef(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
