package ru.job4j.list.ownlist;

import java.util.*;

/**
 * 1. Динамический список на массиве.
 * @author Constantine on 31.10.2021
 */
public class SimpleArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 1;

    /**
     * Данное поле описывает массив,
     * на основе которого будет
     * создаваться список.
     */
    private T[] container;

    /**
     * Данное поле описывает
     * количество элементов в списке.
     */
    private int size;

    /**
     * Данное поле описывает счетчик,
     * который будет увеличиваться
     * после модификации списка.
     */
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Данный метод добавляет новый элемент
     * в конец списка.
     * @param value добавляемое значение.
     */
    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size++] = value;
    }

    /**
     * Данный метод заменяет старое
     * значение в указанной ячейке
     * на новое.
     *
     * Метод whenSetThenGetOldValueAndSizeNotChanged()
     * сломан. Либо я не понимаю, как он работает.
     *
     * @param index индекс.
     * @param newValue новое значение.
     * @return новое значение
     * заменяемой ячейки.
     */
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    /**
     * Данный метод удаляет элемент
     * из списка по индексу.
     * 1.Проверяем, что индекс находится
     * в пределах массива.
     *
     * 2.Выносим некоторые переменные
     * за скобки, потому Checkstyle ругается..
     *
     * 3.Если элемент не последний
     * в списке, то копируем массив,
     * перемещаем все элементы правее
     * на 1 ячейку влево.
     *
     * 4.Последняя ячейка - та что мы
     * сместили в конец (та что удаляется)
     * должна быть стёрта (= null).
     * @param index индекс удаляемой яейки.
     * @return удаленный элемент.
     */
    @Override
    public T remove(int index) {
        modCount++;
        Objects.checkIndex(index, size);
        final Object[] es = container; /* arraycopy не примет дженерик, поэтому создаем Object[]*/
        T oldValue = (T) es[index]; /* сохраняем значение, находящееся в массиве по индексу index */
        final int newSize = size - 1; /* выносим за скобки (чекстайл) */
        if (newSize > index) { /* сдвиг элементы правее индекса на 1 поз влево */
            System.arraycopy(es, index + 1, es, index, newSize - index);
        }
        size = newSize; /* выносим за скобки (чекстайл) */
        es[size] = null;
        return oldValue;
    }

    /**
     * Данный метод возвращает элемент
     * указанной ячейки.
     * @param index индекс ячейки.
     * @return элемент списка.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int expectedModCount = modCount;

            /**
             * Данная переменная позволяет
             * передвигаться по списку.
             */
            int cursor;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            /**
             * Итератор запоминает значение
             * счетчика (modCount) на момент
             * своего создания (expectedModCount),
             * а затем на каждой итерации
             * сравнивает сохраненное значение,
             * с текущим значением поля modCount.
             *
             * 1.Проверяем, чтобы на момент итерирования
             * не была изменена коллекция,
             * иначе выбрасываем исключение.
             * Это называется fail-fast поведение.
             *
             * 2.Создаем счетчик для элементов
             * списка, которые будем возвращать.
             * Указатель {@code cursor} идет вперед,
             * перемещаясь от элемента к элементу.
             * А взвращать нам нужно текущие
             * элементы списка, поэтому
             * добавили переменную {@code i}.
             *
             * @return следующий элемент списка.
             */
            @Override
            public T next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                int i = cursor;
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                cursor = i + 1;
                return (T) container[i];
            }
        };
    }

    /**
     * Данный метод увеличивает емкость
     * массива, чтобы в дальнейшем
     * можно было хранить по крайне мере
     * кол-во элементов = минимальной
     * емкости.
     * @return новый массив с увеличенной емкостью.
     */
    private T[] grow() {
        int oldCapacity = container.length;
        if (oldCapacity == 0) {
            container = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            int newCapacity = oldCapacity * 2;
            container = Arrays.copyOf(container, newCapacity);
        }
        return container;
    }
}
