package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void whenGetMaxIntValue() {
        MaxMin value = new MaxMin();
        List<Integer> list = List.of(1, 3, 5, 7, 10);
        int result = value.max(list, Integer::compareTo);
        assertSame(10, result);
    }

    /**
     * Видимо, максимальное значение -
     * это значение ближе к концу
     * алфавита.
     */
    @Test
    void whenGetMaxStringValue() {
        MaxMin value = new MaxMin();
        List<String> list = List.of("OG", "Secret", "Consta", "LGD", "Liquid");
        String result = value.max(list, String::compareTo);
        assertSame("Secret", result);
    }

    /**
     * Метод instanceOf вызвать не удается.
     * Необходимо написать тест на
     * выброс исключения в случае,
     * если список пустой.
     */
    @Test
    void whenListIsEmptyThenThrowException() {
        MaxMin value = new MaxMin();
        List<String> list = List.of();
        Comparator<String> cmp = String::compareTo;
        assertThatThrownBy(() -> value.max(list, cmp))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Минимальное значение -
     * это значение ближе к началу
     * алфавита.
     */
    @Test
    void whenGetMinStringValue() {
        MaxMin value = new MaxMin();
        List<String> list = List.of("OG", "Secret", "Consta", "LGD", "Liquid");
        String result = value.min(list, String::compareTo);
        assertEquals("Consta", result);
    }

    @Test
    void whenGetMinIntValue() {
        MaxMin value = new MaxMin();
        List<Integer> list = List.of(2, 1, 4, 3);
        int result = value.min(list, Integer::compareTo);
        assertEquals(1, result);
    }
}