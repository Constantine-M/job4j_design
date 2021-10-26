package ru.job4j.generics.task;

/**
 * 0. Что такое обобщенные типы (generics).
 * @author Constantine on 26.10.2021
 */
public class Tiger extends Predator {
    private String species;

    public Tiger(String name, int age, int size, String species) {
        super(name, age, size);
        this.species = species;
    }

    @Override
    public String toString() {
        return "Tiger{"
                + "species='" + species + '\''
                + '}';
    }
}
