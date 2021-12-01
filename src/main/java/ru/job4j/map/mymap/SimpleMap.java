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

    private int threshold;

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
        return middle >> 2;
    }

    private int indexFor(int hash) {
        return 0;
    }

    private void expand() {
        MapEntry<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int newCap = 0;
        int oldThr = 0;
        int newThr = 0;
        if (oldCap > 0) {
            newThr = oldThr;
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = capacity;
            newThr = capacity * (int) LOAD_FACTOR;
        }
        threshold = newThr;
        MapEntry<K, V>[] newTab = new MapEntry[newCap];
        if (oldTab != null) {
            for (int i = 0; i < oldCap; i++) {
                newTab[i] = oldTab[i];
            }
        }
        table = newTab;
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
