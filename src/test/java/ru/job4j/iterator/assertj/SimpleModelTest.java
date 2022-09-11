package ru.job4j.iterator.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Утверждения с исключениями.
 *
 * @author Constantine on 11.09.2022
 */
class SimpleModelTest {

    /**
     * Так можно проверить генерацию исключения,
     * когда метод класса не принимает аргументы.
     */
    void checkGetName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(sm::getName)
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Так проверяется метод, принимающий аргументы.
     */
    void checkName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(() -> sm.setName("name", 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Так можно проверить НАЛИЧИЕ текстового
     * информационного сообщения, сопровождающего
     * исключение.
     */
    @Test
    void  checkMessage() {
        SimpleModel sm = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> sm.setName(word, number))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*проверяем факт наличия сообщения*/
                .message()
                .isNotEmpty();
    }

    /**
     * Так можно проверить СОДЕРЖАНИЕ текстового
     * информационного сообщения, сопровождающее
     * исключение.
     */
    @Test
    void  checkWordMessage() {
        SimpleModel sm = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> sm.setName(word, number))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(word, number)
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("name");
    }
}