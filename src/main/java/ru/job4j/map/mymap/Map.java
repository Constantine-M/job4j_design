package ru.job4j.map.mymap;

/**
 * 8. Реализовать собственную структуру данных - HashMap.
 *
 * @author Constantine on 24.11.2021
 */
public interface Map<K, V> extends Iterable<K> {

    boolean put(K key, V value);
    V get(K key);
    boolean remove(K key);
    int size();
}
