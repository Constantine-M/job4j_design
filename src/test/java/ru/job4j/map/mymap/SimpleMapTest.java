package ru.job4j.map.mymap;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author Constantine on 04.12.2021
 */
public class SimpleMapTest {

    @Test
    public void whenPutThenSizeIncrease() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "rum");
        map.put(2, "whisky");
        map.put(3, "tequila");
        map.put(4, "wine");
        map.put(5, "cognac");
        map.put(6, "champagne");
        map.put(7, "scotch");
        Assert.assertEquals(7, map.size());
    }

    @Test
    public void whenPutWithNullKeyThenSizeIncrease() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(null, "rum");
        map.put(1, "whisky");
        map.put(2, "tequila");
        map.put(3, "wine");
        map.put(4, "cognac");
        map.put(5, "champagne");
        map.put(6, "scotch");
        Assert.assertEquals(7, map.size());
    }

    @Test
    public void whenPutToTheSameIndexThenFalse() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(2, "whisky");
        assertFalse(map.put(6, "champagne"));
    }

    @Test
    public void whenAddAndGetByCorrectKey() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "rum");
        map.put(2, "whisky");
        map.put(3, "tequila");
        Assert.assertEquals("tequila", map.get(3));
    }

    @Test
    public void whenAddNullThenGet() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(null, "gin");
        Assert.assertEquals("gin", map.get(null));
    }

    @Test
    public void whenRemoveValueByNullKey() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(null, "chardonnay");
        assertTrue(map.remove(null));
    }

    @Test
    public void whenGetByIncorrectKeyThenReturnNull() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "liquor");
        map.put(2, "wine");
        assertNull(map.get(3));
    }

    @Test
    public void whenRemoveThenSizeDecrease() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "absinthe");
        map.put(2, "brandy");
        map.remove(2);
        Assert.assertEquals(1, map.size());
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "absinthe");
        map.put(2, "cabernet sauvignon");
        map.put(3, "sauvignon blanc");
        map.put(4, "whisky");
        map.remove(2);
        map.remove(3);
        Assert.assertEquals("absinthe", map.get(1));
        Assert.assertEquals("whisky", map.get(4));
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        Map<Integer, String> map = new SimpleMap<>();
        assertFalse(map.iterator().hasNext());
    }

    @Test(expected =  NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "rum");
        Assert.assertEquals(Integer.valueOf(1), map.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), map.iterator().next());
    }
    @Test
    public void whenCheckIterator() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "rum");
        map.put(2, "whisky");
        map.put(3, "cola");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        map.put(1, "wine");
        iterator.next();
    }
}