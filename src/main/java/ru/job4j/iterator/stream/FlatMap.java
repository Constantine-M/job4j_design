package ru.job4j.iterator.stream;

import java.util.*;

/**
 * FlatMap для Iterator<Iterator>
 * @author Constantine on 04.10.2021
 */
public class FlatMap<T> implements Iterator {

    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * Данный метод проверяет, имеются
     * ли далее элементы.
     * В нашем случае проверяется список
     * списков итераторов, которые
     * содержат числа.
     * 1.Проходим по внутреннему списку
     * пока не закончится {@code cursor != size}.
     * 2.Когда список закончился, то
     * увеличиваем курсор на 1 и
     * переходим к следующему списку.
     * Аналогия с двухмерным массивом.
     * Важно понимать, что список внутри
     * списка пустой/непустой, чтобы
     * можно было переключиться к следующему списку.
     * @return true, если далее имеется элемент.
     */
    @Override
    public boolean hasNext() {
        while (data.hasNext() && !cursor.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }
}
