package ru.job4j.list.listiter;

import java.util.*;
import java.util.function.Predicate;

/**
 * 7. ListIterator.
 *
 * Данный класс описывает принципы
 * работы ListIterator.
 *
 * В качестве альтернативы "обычному" итератору,
 * есть ListIterator. Он обладает fail-safe
 * поведением, это значит, что мы можем
 * менять коллекцию по ходу итерирования,
 * но только с помощью самого итератора.
 *
 * @author Constantine on 11.11.2021
 */
public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Данный метод вставляет элемент
     * после индекса.
     * @param list редактируемый список.
     * @param index индекс элемента.
     * @param value элемент, который
     *              необходимость вставить в список.
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator(list.size());
        while (i.hasPrevious()) {
            if (i.previousIndex() == index) {
                i.add(value);
                break;
            }
            i.previous();
        }
    }

    /**
     * Данный метод удаляет все элементы,
     * которые удовлетворяют предикату.
     *
     * @param list редактируемый список.
     * @param filter уловие для удаления элемента.
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        Objects.requireNonNull(list);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.remove();
            }
        }
    }

    /**
     * Данный метод заменяет все элементы,
     * которые удовлетворяют предикату.
     *
     * @param list редактируемый список.
     * @param filter уловие для замены элемента.
     * @param value значение, которым будем заменять.
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        Objects.requireNonNull(list);
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.set(value);
            }
        }
    }

    /**
     * Данный метод удаляет из списка те
     * элементы, которые есть в elements.
     *
     * 1.Проверяем, что список, из которого
     * что-то хотим удалить, не пустой.
     *
     * 2.Итерироваться будем по
     * редактируемому списку.
     *
     * 3.В цикле проверяем следующее:
     * если возвращаемое итератором
     * значение содержится в списке
     * {@code elements}, то удаляем
     * это значения (ячейку) из
     * редактируемого списка.
     *
     * @param list редактируемый список.
     * @param elements список элементов,
     *                 которые нужно удалить
     *                 из исходного списка.
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        Objects.requireNonNull(list);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (elements.contains(it.next())) {
                it.remove();
            }
        }
    }
}
