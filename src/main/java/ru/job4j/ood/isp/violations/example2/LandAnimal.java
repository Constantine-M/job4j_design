package ru.job4j.ood.isp.violations.example2;

public class LandAnimal implements Animal {

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void molt() {

    }

    @Override
    public void eat() {

    }
}
