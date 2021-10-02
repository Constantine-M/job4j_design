package ru.job4j.iterator.matrix;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MatrixItTest {

    @Test
    public void when1El() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {1}
                }
        );
        assertThat(in.next(), is(1));
    }

    @Test
    public void whenFirstEmptyThenHashNext() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {}, {1}
                }
        );
        assertTrue(in.hasNext());
    }

    @Test
    public void whenFirstEmptyThenNext() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {}, {1}
                }
        );
        assertThat(in.next(), is(1));
    }

    @Test
    public void whenRowHasDiffSize() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {1},
                        {2, 3}
                }
        );
        assertThat(in.next(), is(1));
        assertThat(in.next(), is(2));
        assertThat(in.next(), is(3));
    }

    @Test
    public void when3RowsHasDiffSize() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {},
                        {1, 2, 3},
                        {4},
                }
        );
        assertThat(in.next(), is(1));
        assertThat(in.next(), is(2));
        assertThat(in.next(), is(3));
        assertThat(in.next(), is(4));
    }

    @Test
    public void when3RowsHasDiffSizeThenHasNext() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {},
                        {1, 2, 3},
                        {4},
                }
        );
        assertTrue(in.hasNext());
        assertTrue(in.hasNext());
        assertTrue(in.hasNext());
        assertTrue(in.hasNext());
    }

    @Test
    public void whenFewEmpty() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {1},
                        {},
                        {},
                        {},
                        {2}
                }
        );
        assertThat(in.next(), is(1));
        assertThat(in.next(), is(2));
    }

    @Test
    public void whenEmpty() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {}
                }
        );
        assertFalse(in.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenNext() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {}
                }
        );
        in.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoElementThenNext() {
        MatrixIt in = new MatrixIt(
                new int[][]{}
        );
        in.next();
    }

    @Test
    public void whenMultiHashNext() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {},
                        {1}
                }
        );
        assertTrue(in.hasNext());
        assertTrue(in.hasNext());
    }

    @Test
    public void whenNoElements() {
        MatrixIt in = new MatrixIt(
                new int[][]{
                        {},
                        {},
                        {}
                }
        );
        assertThat(in.hasNext(), is(false));
    }

}