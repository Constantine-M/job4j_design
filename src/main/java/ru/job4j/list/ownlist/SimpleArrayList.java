package ru.job4j.list.ownlist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * 1. Динамический список на массиве.
 * @author Constantine on 31.10.2021
 */
public class SimpleArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

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
        container[size] = value;
        size += 1;
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
        newValue = container[index];
        return newValue;
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
        final Object[] es = container;
        T oldValue = (T) es[index];
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(es, index + 1, es, index, newSize - index);
        }
        size = newSize;
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
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    /**
     * Данный метод увеличивает емкость
     * массива, чтобы в дальнейшем
     * можно было хранить по крайне мере
     * кол-во элементов = минимальной
     * емкости.
     *
     * Наше временное хранилище парметризовано
     * дженериком - не получается создать
     * новый массив.
     *
     * @return новый массив с увеличенной емкостью.
     */
    private T[] grow() {
        int oldCapacity = container.length;
        if (oldCapacity > 0 || container != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = oldCapacity * 2;
            container = Arrays.copyOf(container, newCapacity);
        } else {
            container = (T[]) new Object[DEFAULT_CAPACITY];
        }
        return (T[]) container;
    }
}
