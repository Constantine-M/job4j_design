package ru.job4j.map.mymap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 8. Реализовать собственную структуру данных - HashMap.
 *
 * @author Constantine on 24.11.2021
 */
public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int modCount = 0;

    /**
     * Данная переменная поля описывает
     * кол-во элементов в карте.
     */
    private int size = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Данный метод добавляет пару
     * ключ-значение в нашу карту (массив).
     *
     * Метод максимально упрощен в
     * учебных целях.
     * Здесь нет разрешения коллизий.
     * Здесь не реализуется связный список.
     *
     * 1.Расширяем массив.
     * 2.Находим индекс ячейки, куда
     * будет помещена пара.
     * 3.Если ячейка пустая, то
     * добавляем пару ключ-значение.
     *
     * @param key ключ.
     * @param value значение.
     * @return true, если пара добавилась,
     *         false - если нет.
     */
    @Override
    public boolean put(K key, V value) {
        boolean rsl;
        int index = 0;
        if (key != null) {
            index = indexFor(hash(key.hashCode()));
        }
        expand();
        if ((table[index]) == null) {
            table[index] = new MapEntry(key, value);
            rsl = true;
        } else {
            rsl = false;
        }
        modCount++;
        size++;
        return rsl;
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
        int middle = (square % 1234) + 77;
        return middle >> 2;
    }

    /**
     * Данный метод возвращает индекс
     * ячейки таблицы.
     *
     * Так как хэш код может быть большим,
     * а исходный массив маленький - нужно
     * хэш код трансформировать в значения
     * от 0 до 7 (если емкость таблицы = 8).
     *
     * @param hash хэш значение ключа.
     * @return индекс ячейки.
     */
    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    /**
     * Данный метод возвращает кол-во
     * элементов в карте.
     *
     * @return количество элементов в карте.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Данный метод расширяет массив.
     *
     * Если емкость массива достигла
     * значения {@code threshold},
     * то увеличивем емкость карты в
     * 2 раза.
     */
    private void expand() {
        int threshold = (int) (LOAD_FACTOR * capacity);
        int newCap = 0;
        if (size >= threshold) {
            newCap = capacity * 2;
            MapEntry<K, V>[] newTab = new MapEntry[newCap];
            for (int i = 0; i < capacity; i++) {
                newTab[i] = table[i];
            }
            capacity = newCap;
            table = newTab;
        }
    }

    /**
     * Данный метод возвращает значение
     * по ключу.
     *
     * 1.По ключу находим индекс ячейки.
     * 2.Возвращаем значение данной ячейки.
     *
     * @param key ключ.
     * @return значение ячейки.
     */
    @Override
    public V get(K key) {
        int index = 0;
        if (key != null) {
            index = indexFor(hash(key.hashCode()));
        }
        MapEntry<K, V> element;
        if (table[index] != null) {
            element = table[index];
            return element.value;
        }
        return null;
    }

    /**
     * Данный метод удаляет пару
     * ключ-значение из карты.
     *
     * 1.Находим индекс по ключу.
     * 2.Так как пары записываются
     * в массив, то чтобы удалить
     * пару, мы просто обнуляем ячейку.
     *
     * @param key ключ.
     * @return true, если элемент был удален.
     */
    @Override
    public boolean remove(K key) {
        boolean rsl;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            table[index] = null;
            rsl = true;
            size--;
            modCount++;
        } else {
            rsl = false;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return  new Iterator<K>() {

            int expectedModCount = modCount;

            /**
             * Данная переменная является указателем
             * и позволяет перемещаться по ячейкам
             * карты, которые содержат пару
             * ключ-значение.
             */
            int cursor;

            /**
             * Итератор запоминает значение
             * счетчика (modCount) на момент
             * своего создания (expectedModCount),
             * а затем на каждой итерации
             * сравнивает сохраненное значение,
             * с текущим значением поля modCount.
             *
             * Проверяем, чтобы на момент итерирования
             * не была изменена наша карта,
             * иначе выбрасываем исключение.
             * Это называется fail-fast поведение.
             *
             * Так как заполнение карты неравномерное,
             * то пустые ячейки нужно пропускать.
             */
            @Override
            public boolean hasNext() {
                boolean rsl;
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length) {
                    if (table[cursor] == null) {
                        cursor++;
                    } else {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
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
