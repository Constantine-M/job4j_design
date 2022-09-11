package ru.job4j.iterator.assertj;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Утверждения с исключениями.
 *
 * @author Constantine on 11.09.2022
 */
class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty", names);
    }

    @Test
    void whenDoesNotContainEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"Consta Mezenin", "Thor Odinson"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol", names);
    }

    @Test
    void whenDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"=Mezenin", "Thor=Odinson"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a key", names);
    }

    @Test
    void whenDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"Consta=", "Thor=Odinson"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value", names);
    }
}