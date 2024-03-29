package ru.job4j.ood.isp.violations.example1;

/**
 * Данный пример нарушает
 * принцип разделения интерфейсов.
 * В нашем случае мы не можем
 * перезарядить нож.
 * Для решения этой проблемы
 * необходимо выделять интерфейс
 * огнестрела и интерфейс холодного
 * оружия.
 */
public interface Weapons {

    void attack();
    void reload();
}