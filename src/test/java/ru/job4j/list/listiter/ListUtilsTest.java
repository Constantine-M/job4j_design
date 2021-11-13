package ru.job4j.list.listiter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Constantine on 11.11.2021
 */
public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(list, 1, 2);
        assertThat(list, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveAllFivesFromList() {
        List<Integer> list = new ArrayList<>(
                Arrays.asList(0, 5, 1, 5, 2, 5, 3)
        );
        Predicate<Integer> pred = i -> i.equals(5);
        ListUtils.removeIf(list, pred);
        assertThat(list, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = NullPointerException.class)
    public void whenRemoveFromEmptyList() {
        List<Integer> list = null;
        Predicate<Integer> pred = i -> i.equals(5);
        ListUtils.removeIf(list, pred);
    }

    @Test
    public void whenReplace2to5() {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 13, 4, 2));
        Predicate<Integer> pred = i -> i.equals(2);
        ListUtils.replaceIf(list, pred, 5);
        assertThat(list, is(Arrays.asList(5, 13, 4, 5)));
    }

    @Test
    public void whenReplaceFirstAndLastEl() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 0, 1));
        Predicate<Integer> pred = i -> i.equals(1);
        ListUtils.replaceIf(list, pred, 5);
        assertThat(list, is(Arrays.asList(5, 0, 5)));
    }

    @Test
    public void whenRemove1And5FromList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 1, 5, 3, 2, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 5));
        ListUtils.removeAll(list, elements);
        assertThat(list, is(Arrays.asList(2, 3, 4, 3, 2)));
    }

    @Test
    public void whenRemoveSecondHalfList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> elements = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10));
        ListUtils.removeAll(list, elements);
        assertThat(list, is(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void whenRemoveSingleElThenEmpty() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1));
        ListUtils.removeAll(list, elements);
        assertTrue(list.isEmpty());
    }
}