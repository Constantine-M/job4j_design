package ru.job4j.iterator.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Подключение библиотеки AssertJ.
 *
 * Если во время сборки проекта, IDEA
 * не найдет старую библиотеку Junit,
 * используй бибилиотеку Vintage.
 * Это нужно для мягкой миграции с
 * Junit 4 на Junit 5.
 * <a href="https://mvnrepository.com/artifact/org.junit.vintage/junit-vintage-engine/5.9.0"></a>
 *
 * @author Constantine on 11.09.2022
 */
class ArrayItTest {

    @Test
    void whenMultiCallHasNextThenTrue() {
        ArrayIt array = new ArrayIt(
                new int[] {1, 2, 3}
        );
        boolean rsl = array.hasNext();
        assertThat(rsl).isTrue();
        assertThat(array.hasNext()).isTrue();
    }

    @Test
    void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
    }
}