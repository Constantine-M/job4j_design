package ru.job4j.iterator.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Утверждения с примитивными типами.
 *
 * @author Constantine on 11.09.2022
 */
class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEqualTo("Cube")
                .isNotEqualTo("Tetrahedron");
    }

    @Test
    void whenThereAre8Vertices() {
        Box box = new Box(8, 8);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(8)
                .isNotZero()
                .isLessThan(10);
    }

    @Test
    void whenObjectIsNotExists() {
        Box box = new Box(-1, 0);
        boolean exist = box.isExist();
        assertThat(exist).isFalse()
                .isEqualTo(false)
                .isNotEqualTo(true);
    }

    @Test
    void whenAreaApproximatelyEqualTo3215() {
        Box box = new Box(0, 16);
        double area = box.getArea();
        assertThat(area).isEqualTo(3216.990877275948)
                .isCloseTo(3215, Percentage.withPercentage(0.07))
                .isGreaterThan(3200);
    }
}