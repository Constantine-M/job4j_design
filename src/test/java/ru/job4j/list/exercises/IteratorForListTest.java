package ru.job4j.list.exercises;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Constantine on 02.11.2021
 */
public class IteratorForListTest {
    @Test
    public void when() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        IteratorForList.main(null);
        String ln = System.lineSeparator();
        String expected = "Next element has - true, this element is one" + ln
                + "Next element has - true, this element is two" + ln
                + "Next element has - true, this element is three" + ln
                + "Next element has - true, this element is four" + ln
                + "Next element has - true, this element is five" + ln
                + "Next element has - false" + ln;
        assertThat(out.toString(), is(expected));
    }
}