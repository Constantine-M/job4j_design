package ru.job4j.gc.profiling;

/**
 * 1. Эксперименты с различными GC.
 * В этом задании мы будем профилировать
 * различные типы сортировки.
 *
 * @author Constantine on 31.07.2022
 */
public interface Data {

    void insert(int elements);

    int[] getClone();
}
