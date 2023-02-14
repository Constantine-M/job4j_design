package ru.job4j.ood.dip.violations.post;

public class App {

    public static void main(String[] args) {
        Storage store = new Storage(new PostgreStorage());
    }
}
