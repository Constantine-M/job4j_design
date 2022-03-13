package ru.job4j.serialization.json;

import java.util.Arrays;

/**
 * 2. Формат JSON.
 *
 * Данный класс описывает банк.
 * У него есть имя, капитализация,
 * список услуг, счета. И он либо
 * коммерческий, либо нет.
 *
 * @author Constantine on 13.03.2022
 */
public class Bank {

    private final String name;
    private final boolean commercial;
    private final int capitalization;
    private final Account account;
    private final String[] services;

    public Bank(String name, boolean commercial, int capitalization, Account account, String[] services) {
        this.name = name;
        this.commercial = commercial;
        this.capitalization = capitalization;
        this.account = account;
        this.services = services;
    }

    @Override
    public String toString() {
        return "Bank{"
                + "name='" + name + '\''
                + ", commercial=" + commercial
                + ", capitalization=" + capitalization
                + ", account=" + account
                + ", services=" + Arrays.toString(services)
                + '}';
    }
}
