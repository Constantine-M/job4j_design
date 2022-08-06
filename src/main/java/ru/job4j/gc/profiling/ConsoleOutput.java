package ru.job4j.gc.profiling;

/**
 * 1. Эксперименты с различными GC.
 *
 * @author Constantine on 31.07.2022
 */
public class ConsoleOutput implements Output {

    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }
}
