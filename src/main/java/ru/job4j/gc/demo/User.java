package ru.job4j.gc.demo;

/**
 * 1. Демонстрация работы GC.
 *
 * @author Constantine on 24.07.2022
 */
public class User {

    private int age;

    private String name;

    /**
     * Данное поле описывает прогресс
     * прохождения курса в процентах.
     */
    private double percentage;

    public User(int age, String name, double percentage) {
        this.age = age;
        this.name = name;
        this.percentage = percentage;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Object that has been removed - %s%n", name);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "User{"
                + "age=" + age
                + ", name='" + name + '\''
                + ", percentage=" + percentage
                + '}';
    }
}
