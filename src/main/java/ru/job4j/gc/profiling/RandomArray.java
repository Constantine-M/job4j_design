package ru.job4j.gc.profiling;

import java.util.Arrays;
import java.util.Random;

/**
 * 1. Эксперименты с различными GC.
 * В этом задании мы будем профилировать
 * различные типы сортировки.
 *
 * 1.Данный класс описывает данные,
 * с которыми будем работать.
 *
 * 2.Задаем количеством элементов, из
 * которого сгенерируется массив int[],
 * заполненный случайными числами.
 *
 * 3.Для каждой сортировки будет
 * использоваться копия первоначального
 * массива, чтобы мы сортировали
 * один и тот же массив.
 *
 * @author Constantine on 31.07.2022
 */
public class RandomArray implements Data {

    public int[] array;

    private Random random;

    public RandomArray(Random random) {
        this.random = random;
    }

    @Override
    public void insert(int elements) {
        array = new int[elements];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(elements - 1) + 1;
        }
    }

    @Override
    public int[] getClone() {
        return array.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
