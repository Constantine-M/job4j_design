package ru.job4j.map.mymap;

import java.util.Iterator;

/**
 * 8. Реализовать собственную структуру данных - HashMap.
 *
 * @author Constantine on 24.11.2021
 */
public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        return false;
    }

    /**
     * Данный метод находит хэш значение
     * определенного объекта.
     *
     * @param hashCode хэшкод, который
     *                 похоже, указываем сами.
     * @return хэш значение.
     */
    private int hash(int hashCode) {
        int square = hashCode * hashCode;
        int middle = (square % 1000000) / 100;
        return middle;
    }

    private int indexFor(int hash) {
        return 0;
    }

    private void expand() {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
