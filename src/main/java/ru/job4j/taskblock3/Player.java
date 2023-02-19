package ru.job4j.taskblock3;

/**
 * Данная модель описывает игрока.
 * У него есть имя и фигура,
 * которой он играет (крестик
 * или нолик).
 */
public class Player {

    private final String name;

    private final Figure figure;

    public Player(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
    }
}
