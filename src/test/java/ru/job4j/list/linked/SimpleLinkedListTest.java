package ru.job4j.list.linked;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Constantine on 05.11.2021
 */
public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.iterator().next();
    }

    /**
     * Если с момента создания итератора коллекция
     * подверглась структурному изменению, итератор
     * должен кидать {@link ConcurrentModificationException}.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        iterator.next();
    }

    @Test
    public void whenAddIterHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenAddIterNextOne() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenEmptyIterHashNextFalse() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddIterMultiHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenAddIterNextOneNextTwo() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is(1));
        assertThat(first.hasNext(), is(true));
        assertThat(first.next(), is(2));
        assertThat(first.hasNext(), is(false));
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is(1));
        assertThat(second.hasNext(), is(true));
        assertThat(second.next(), is(2));
        assertThat(second.hasNext(), is(false));
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(null);
        list.add(null);
        assertNull(list.get(0));
        assertNull(list.get(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.deleteFirst();
        list.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        List<Integer> list = new SimpleLinkedList<>();
        list.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.deleteFirst(), is(1));
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddThenIter() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenAdd3ElAndRevertThenIter() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        SimpleLinkedList<Integer> emtyList = new SimpleLinkedList<>();
        assertFalse(emtyList.revert());
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        SimpleLinkedList<Integer> singleList = new SimpleLinkedList<>();
        singleList.add(1);
        assertFalse(singleList.revert());
    }
}