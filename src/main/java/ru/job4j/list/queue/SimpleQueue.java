package ru.job4j.list.queue;

import ru.job4j.list.stack.SimpleStack;

import java.util.NoSuchElementException;

/**
 * 5. Очередь на двух стеках.
 *
 * Первый стек (in) - входной.
 * Второй стек (out) - выходной.
 *
 * У вас стопка с тарелками. Вам нужно
 * достать нижнюю тарелку. Для этого вы
 * перекладываете все тарелки в другую стопку.
 *
 * @author Constantine on 08.11.2021
 */
public class SimpleQueue<E> {

    private final SimpleStack<E> in = new SimpleStack<>();
    private final SimpleStack<E> out = new SimpleStack<>();

    /**
     * Данный метод удаляет первое
     * значение из коллекции.
     *
     * 1.На входе очередь {1, 2, 3} снизу
     * вверх. Чтобы достать {1}, нужно
     * всю стопку переложить в {out}.
     *
     * 2.Пока {@code in} не опустеет,
     * перекладываем тарелки. Добавляем то,
     * что снимаем сверху от {@code in}.
     * На выход получим очередь {3, 2, 1}
     * снизу вверх.
     *
     * 3.Снимаем тарелку {1} сверху
     * и возвращаем значение.
     *
     * @return первое значение.
     */
    public E poll() {
        if (out.size() == 0 && in.size() == 0) {
            throw new NoSuchElementException();
        }
        if (out.size() == 0) {
            while (in.size() > 0) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Данный метод помещает
     * значение в конец.
     */
    public void push(E value) {
        in.push(value);
    }
}
