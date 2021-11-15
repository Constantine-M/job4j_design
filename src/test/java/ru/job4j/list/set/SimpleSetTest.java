package ru.job4j.list.set;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Constantine on 14.11.2021
 */
public class SimpleSetTest {

    @Ignore
    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Ignore
    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Ignore
    @Test
    public void whenAddNullAndNumberAndDublcateIt() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.add(1));
        assertTrue(set.add(5));
        assertTrue(set.contains(null));
        assertTrue(set.contains(1));
        assertTrue(set.contains(5));
        assertFalse(set.add(null));
        assertFalse(set.add(5));
    }
}