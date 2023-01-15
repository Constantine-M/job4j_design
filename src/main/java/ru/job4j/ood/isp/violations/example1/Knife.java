package ru.job4j.ood.isp.violations.example1;

public class Knife implements Weapons {
    @Override
    public void attack() {
        System.out.println("Сarelessly hack and slash..");
    }

    @Override
    public void reload() {
        throw new UnsupportedOperationException("Knife cant do that..");
    }
}
