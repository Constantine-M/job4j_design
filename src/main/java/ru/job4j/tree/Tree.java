package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 1. Создать элементарную структуру дерева.
 *
 * Данный интерфейс описыает наше дерево
 * согласно требованиям задания.
 *
 * @author Constantine on 07.12.2021
 */
public interface Tree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    boolean isBinary();

    /**
     * Данный класс описывает узел
     * дерева. Узел содержит значение
     * ячейки и ссылки на дочерние узлы.
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}
