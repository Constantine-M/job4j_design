package ru.job4j.io.fileconfig;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Constantine on 12.01.2022
 */
public class ConfigTest {

    @Ignore
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Consta Mezenin"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }
}