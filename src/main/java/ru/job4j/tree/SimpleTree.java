package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

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
     * Данный метод возвращает Optional<Node>,
     * а не Node<E>. В итоге пока что не ясно,
     * где следует поправить.
     * Где-то нам нужно из простого узла
     * вернуть в итоге узел конкретного типа
     * Node<E>.
     * @param condition
     * @return
     */
    private Optional<Node> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        Predicate<Node<E>> pred = E -> E.children.size() > 2;
        return findByPredicate(pred).isEmpty();
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
        return Optional.empty();
    }

   /* @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> pred = E -> E.value.equals(value);
        return findByPredicate(pred);
        System.out.println("-------------------------");
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
    }*/

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
