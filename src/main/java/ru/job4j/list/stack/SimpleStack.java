package ru.job4j.list.stack;

import ru.job4j.list.linked.SimpleLinkedList;

/**
 * 4. Используя контейнер на базе связанного
 * списка создать контейнер Stack.
 *
 * Данный класс описывает структуру Stack.
 *
 * Stack - примитивная структура данных.
 * Работу этой структуры можно представить
 * в виде тарелок поставленных друг на
 * друга. Новая тарелка всегда оказывается
 * верхней. Если нам нужно достать тарелку
 * из середины стопки, то нужно снять
 * верхние тарелки.
 *
 * @author Constantine on 07.11.2021
 */
public class SimpleStack<E> {

    private SimpleLinkedList<E> linked = new SimpleLinkedList<>();

    /**
     * Данный метод "снимает тарелку сверху".
     *
     * @return удаленный узел из стэка.
     */
    public E pop() {
        return linked.deleteFirst();
    }

    /**
     * Данный метод "кладет тарелку наверх".
     *
     * @param element значение, добавляемое в список.
     */
    public void push(E element) {
        linked.addFirst(element);
    }
}
