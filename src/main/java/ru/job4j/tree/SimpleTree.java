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
     * Данный метод представляет из себя
     * шаблон. Шаблон проходит дерево
     * в ширину.
     *
     * В шаблоне можно изменять условие,
     * которое выполняется или нет в
     * процессе прохождения по дереву.
     *
     * Коротко, прохождение в ширину
     * заключается в обходе дерева по
     * уровням.
     *
     * 1.Каждый уровень, начиная с
     * {@code root} элемента, представляет
     * из себя связный список.
     *
     * 2.Добавляем элементы связного
     * списка в очередь.
     *
     * 3.Начинаем обход этого списка
     * согласно очереди.
     *
     * 4.Пока очередь не опустела,
     * извлекаем элемент и проверяем
     * наше условие.
     *
     * 5.Если условие выполняется, то
     * возвращаем наш узел.
     *
     * 6.Если условие не выполняет, а
     * очередь заканчивается, то
     * переходим на уровень ниже,
     * добавляя всех детей всех элементов
     * очереди (предыдущей) и проходим
     * по новой очередт снова.
     *
     * @param condition условие возврата
     *                  узла из очереди.
     * @return узел связного списка из очереди.
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
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

    /**
     * Данный метод проверяет, что
     * дерево бинарное.
     *
     * Предикат построен таким образом, что
     * если у элемента больше 2 детей,
     * то функция {@code findByPredicate}
     * вернет этот узел (элемент).
     *
     * Это будет означать, что дерево
     * не бинарное.
     *
     * Если вернет {@code null} в обертке
     * {@link Optional} - соотв-но
     * дерево бинарное (потому что
     * не были найдены узлы, где больше
     * 2 потомков).
     *
     * Отсюда, чтобы проверить, что
     * дерево бинарное, нужно проверить,
     * что метод вернет пустоту (а не узел).
     *
     * @return true, если дерево бинарное.
     */
    @Override
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
        Predicate<Node<E>> pred = E -> E.value.equals(value);
        return findByPredicate(pred);
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
