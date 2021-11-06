package ru.job4j.list.linked;

import java.util.*;

/**
 * 2. Создать контейнер на базе связанного списка.
 * Данный класс описывает общую работу
 * связного списка.
 *
 * Так как по задаче требуется добавить
 * узел только в конец, то список будет
 * односвязным и в конструкторе ноды
 * не требуется указывать ссылку
 * на предыдущий элемент.
 *
 * @author Constantine on 04.11.2021
 */
public class SimpleLinkedList<E> implements List<E> {

    private int size;

    private int modCount;

    /**
     * Данное поле описывает голову
     * связного списка.
     */
    private Node<E> firstNode;

    /**
     * Данноеполе описывает хвост
     * связного списка.
     */
    private Node<E> lastNode;

    /**
     * Данный метод добавляет узел в список.
     *
     * С одной нодой (firstNode) не прокатило -
     * добавляю вторую ноду (lastNode).
     * Иначе наталкиваемся на NPE.
     *
     * 1.Если узел первый и единственный (null),
     * то он является первым и последним.
     *
     * 2.Если узел не первый, то
     * новый узел станет хвостом.
     * Т.к. голова не пустая, то
     * к ней прицепляем ссылку на
     * следующий элемент.
     *
     * @param element элемент, который добавляется
     *                в список.
     */
    @Override
    public void add(E element) {
        Node<E> last = lastNode;
        Node<E> newNode = new Node<>(element, null);
        lastNode = newNode;
        if (last == null) {
            firstNode = newNode;
        } else {
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     *
     * 1.Проходим простым циклом по
     * списку узлов от 0 до index.
     * index не включаем.
     *
     * 2.Во время прохождения по списку
     * присваиваем ноде значение
     * следующей ноды.
     *
     * @param index индекс ячейки в списке.
     * @return значения ячейки по индексу.
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = firstNode;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    /**
     * В итераторе также без "головы"
     * и "хвоста" не обойтись, поэтому
     * объявляем 2 узла {@code lastReturned}
     * и {@code next}.
     *
     * @return итератор связного списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            /**
             * Элемент, который вернули последним...
             * вернули в текущей итерации.
             */
            private Node<E> lastReturned = firstNode;

            /**
             * Элемент, который будет возвращен
             * в следующей итерации.
             */
            private Node<E> next = firstNode;

            /**
             * Данное поле описывает указатель,
             * который передвигается по списку.
             */
            int cursor;

            int expectedModCount = modCount;

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
             */
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            /**
             * Даннйы метод возвращает следующий
             * элемент списка.
             *
             * В этом методе происходит
             * перелинковка ссылок.
             * 1.Последний возвращенный элемент
             * становится следующим - его мы
             * будем "выводить на консоль".
             * 2.Следующий элемент вызывает
             * свой метод {@code next} и его
             * ссылка меняется.
             * 3.Двигаем курсор, чтобы метод
             * {@code hasNext} отрабатывал корректно.
             *
             * @return следующий элемент списка.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = next;
                next = next.next;
                cursor++;
                return lastReturned.item;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
