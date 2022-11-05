package ru.job4j.ood.template;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class GreetingsGeneratorTest {

    String template = "Call me ${hero}, and you, ${mortal}, isn't it?";
    Map<String, String> map = new HashMap<>();
    GreetingsGenerator generator = new GreetingsGenerator();

    @Disabled
    @Test
    public void whenGetCompleteTemplate() {
        map.put("hero", "Hagrid");
        map.put("mortal", "Consta");
        String expected = "Call me Hagrid, and you, Consta, isn't it?";
        String result = generator.produce(template, map);
        assertEquals(expected, result);
    }

    /**
     * Here is the "commoner" key instead
     * of "mortal".
     */
    @Disabled
    @Test
    public void whenAnyKeyIsInvalidThenThrowException() {
        map.put("hero", "Hagrid");
        map.put("commoner", "Consta");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Here is map has 1 pair instead of 2 pairs.
     */
    @Disabled
    @Test
    public void whenMapHasOnePairThenException() {
        map.put("hero", "Hagrid");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Here is map has 3 pairs.
     * One pair extra.
     */
    @Disabled
    @Test
    public void whenMapHasExtraPairsThenException() {
        map.put("hero", "Hagrid");
        map.put("mortal", "Consta");
        map.put("bebebe", "goat");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }
}