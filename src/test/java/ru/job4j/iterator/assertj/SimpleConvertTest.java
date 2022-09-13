package ru.job4j.iterator.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 * Утверждения с коллекциями.
 *
 * @author Constantine on 13.09.2022
 */
class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .containsExactly("first", "second", "three", "four", "five")
                .noneSatisfy(e -> {
                    assertThat(e).isEqualTo("six"); })
                .startsWith("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Liquor", "Rum", "Whisky", "Vine", "Champagne");
        assertThat(set).hasSize(5)
                .doesNotHaveDuplicates()
                .allSatisfy(e -> {
                    assertThat(e).isNotEqualTo("balalaika"); })
                .anyMatch(e -> e.equals("Rum"));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap(
                "Рулон Обоев", "Антикор Порогов", "Залог Успехов", "Улов Налимов");
        assertThat(map).hasSize(4)
                .containsEntry("Залог Успехов", 2)
                .doesNotContainKey("Vine")
                .containsKeys("Улов Налимов", "Рулон Обоев")
                .containsValue(0);
    }
}