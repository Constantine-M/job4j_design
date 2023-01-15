package ru.job4j.ood.isp.violations.example1;

public class Gun implements Weapons {
    @Override
    public void attack() {
        System.out.println("Bang-bang!");
    }

    @Override
    public void reload() {
        System.out.println("Reload the gun..");
    }
}
