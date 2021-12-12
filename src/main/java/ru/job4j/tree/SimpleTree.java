package ru.job4j.tree;

import java.util.*;

/**
 * 1. Создать элементарную структуру дерева.
 * @author Constantine on 07.12.2021
 */
public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Данный метод находит узел по значению
     * {@code parent} и добавляет в него
     * дочерний узел со значением {@code child}.
     *
     * Так как метод {@code findBy()} возвращает
     * {@link Optional}, то код null-safe.
     *
     * 1.У родителя дети представлены в виде
     * списка.
     * 2.Нужно, чтобы соблюдалось 2 условия:
     * родитель есть, а дочернего узла нет -
     * тогда мы добавляем дочерний узел.
     * Иначе не добавляем.
     *
     * @param parent родительский узел.
     * @param child дочерний узел.
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> papa = findBy(parent);
        boolean rsl = papa.isPresent() && findBy(child).isEmpty();
        List<Node<E>> sons = papa.get().children;
        return rsl ? sons.add(new Node<>(child)) : false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleTree<?> that = (SimpleTree<?>) o;
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}
