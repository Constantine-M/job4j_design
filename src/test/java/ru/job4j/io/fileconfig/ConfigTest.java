package ru.job4j.io.fileconfig;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Constantine on 12.01.2022
 */
public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Consta Mezenin"));
        assertThat(config.value("surname"), is(""));
    }

    @Test
    public void whenPairWithCommentsAndStrings() {
        String path = "./data/pair_with_comments_and_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("car"), is("Honda Accord"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithCommentsAndWrongTemplateInKeyPart() {
        String path = "./data/pair_with_comments_and_wrong_template.properties";
        Config config = new Config(path);
        config.load();
        config.value("key");
    }
}