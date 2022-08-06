package ru.job4j.gc.profiling;

/**
 * 1. Эксперименты с различными GC.
 * В этом задании мы будем профилировать
 * различные типы сортировки.
 *
 * В данном классе описывается
 * cортировка методом вставки.
 *
 * @author Constantine on 31.07.2022
 */
public class InsertSort implements Sort {

    @Override
    public boolean sort(Data data) {
        int[] array = data.getClone();
        sort(array);
        return true;
    }

    private void sort(int[] array) {
        int in, out;
        for (out = 1; out < array.length; out++) {
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
    }
}
