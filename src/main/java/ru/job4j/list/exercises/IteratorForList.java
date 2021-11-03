package ru.job4j.list.exercises;

import java.util.Iterator;
import java.util.List;

/**
 * 1.2. Итератор для списка.
 *
 * 1.Что это за Iterator такой спросите Вы?
 * Это объект который с помощью своих методов
 * позволяет последовательно обойти все
 * элементы, которые он в себе содержит.
 *
 * 2.Всегда перед вызовом метода E next()
 * надо использовать метод hasNext(),
 * иначе будет сгенерировано
 * исключение NoSuchElementException.
 *
 * 3.boolean hasNext() - метод проверяет
 * содержит ли итератор следующий элемент,
 * возвращает false, если курсор станет
 * равным размеру списка, т.е.
 * следующего элемента не существует.
 *
 * 4.ВАЖНО! После того, как был получен
 * объект Iterator<E>, нельзя производить
 * никаких модификаций с исходным списком,
 * поскольку при последующем вызове
 * метода next() будет сгенерировано
 * исключение ConcurrentModificationException,
 * которое говорит о том, что в процессе
 * обхода коллекции была произведена ее
 * модификация, а это недопустимо.
 *
 * @author Constantine on 02.11.2021
 */
public class IteratorForList {
    public static void main(String[] args) {
        List<String> list = List.of("one", "two", "three", "four", "five");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("Next element has - " + iterator.hasNext() + ", this element is " + iterator.next());
        }
        System.out.println("Next element has - " + iterator.hasNext());
    }
}
