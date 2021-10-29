package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 0.1. Интерфейс List.
 * @author Constantine on 29.10.2021
 */
public class ListUsage {
    /**
     * Данный метод описывает методы
     * интерфейса {@link List}.
     *
     * CREATE
     * 1.add - добавление в конец списка.
     * 2.{@code void add(int index, E element)} -
     * добавляет указанный элемент (element) в
     * указанную позицию(index) в списке.
     * При этом сдвигает элемент, который
     * находится в этой позиции(если есть),
     * и все последующие элементы вправо.
     *
     * 3.{@code boolean addAll(Collection<? extends E> c)} -
     * добавляет все элементы из переданной
     * коллекции в конец списка в том порядке,
     * в котором они возвращаются итератором
     * переданной коллекции.
     *
     * 4.boolean addAll(int index, Collection<? extends E> c) -
     * добавляет все элементы из коллекции c
     * в список в указанную позицию(index).
     * При этом сдвигает элемент, который
     * аходится в этой позиции, и все
     * последующие элементы вправо.
     *
     * 5.List<E> of(E ... elements) -
     * метод возвращает список, в которые
     * помещены список элементов elements типа E.
     * Данный список неизменяемый.
     *
     * READ
     * 1.E get(int index) - метод возвращает
     * элемент, который находится в позиции
     * index в этом списке.
     *
     * 2.1.Iterator<E> iterator() - метод
     * возвращает объект Iterator, который
     * содержит в себе все элементы
     * исходной коллекции.
     * 2.2.ListIterator<E> listIterator() -
     * возвращает итератор списка для
     * элементов в этом списке. Вывод в
     * консоль будет аналогичным 2.1.
     * 2.3.ListIterator<E> listIterator(int index) –
     * возвращает итератор списка для элементов
     * в этом списке, начиная с элемента
     * индекс которого равен index.
     *
     * UPDATE
     * 1.E set(int index, E element) – заменяет
     * элемент позиция которого равна index на
     * элемент который мы передаем в метод (element).
     * При этом метод возвращает старое
     * значение элемента с индексом index.
     *
     * 2.default void replaceAll(UnaryOperator<E> operator) -
     * заменяет каждый элемент в списке
     * результатом применения оператора
     * (operator) к каждому элементу.
     *
     * DELETE
     * 1.E remove(int index) – удаляет элемент
     * из списка по индексу index, при этом
     * метод возвращает удаленный элемент.
     *
     * 2.boolean remove(E e) – удаляет элемент е
     * типа E из коллекции при его ПЕРВОМ
     * вхождении в список, если он есть в коллекции.
     *
     * 3.boolean removeAll(Collection<?> col) –
     * метод удаляет из списка все элементы,
     * которые содержатся в коллекции col,
     * если в результате работы метода
     * исходный список изменился -
     * метод возвращает true.
     *
     * 4.boolean retainAll(Collection<?> col) –
     * метод также удаляет элементы из списка,
     * за исключением тех, которые находятся
     * в коллекции col, если в результате
     * работы метода исходный список
     * изменился - метод возвращает true.
     *
     * 5.default boolean removeIf(Predicate<? super E> filter) –
     * метод удаляет все элементы из коллекции,
     * которые удовлетворяют условию
     * определенному в предикате filter
     * (передается в виде лямбда выражения).
     *
     * USEFUL
     * 1.int lastIndexOf*(E element) - метод
     * возвращает индекс элемента element
     * при его последнем вхождении в список.
     *
     * 2.List<E> subList(int fromIndex, int toIndex) -
     * метод возвращает список, который
     * содержит все элементы исходного
     * списка начиная с индекса fromIndex
     * (включительно) и до toIndex(значение
     * исключается). При этом, если выполняется
     * условие fromIndex == toIndex,
     * то метод вернет пустой список.
     *
     * Методы ниже реализованы с помощью цикла for(),
     * поэтому применять эти методы внутри циклов,
     * которые перебирают список, нежелательно,
     * поскольку так мы будем проходить по
     * одному и тому же списку дважды.
     *
     * 1.indexOf*(E element)
     * 2.lastIndexOf*(E element)
     * 3.contains*(E element)
     */
    public static void main(String[] args) {
        System.out.println("ДОБАВЛЕНИE: ");
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add(1, "airport");
        List<String> list1 = new ArrayList<>();
        list1.add("fifth");
        list1.add("sixth");
        list.addAll(list1);
        List<String> list2 = new ArrayList<>();
        list2.add("notebook");
        list2.add("monitor");
        list.addAll(1, list2);
        for (String str : list) {
            System.out.println("Текущий элемент - " + str);
        }
        List<String> listOf = List.of("one", "two", "three");
        System.out.println("ЧТЕНИЕ: ");
        for (int index = 0; index < listOf.size(); index++) {
            System.out.println("Текущий элемент - " + listOf.get(index));
        }
        Iterator<String> it = listOf.iterator();
        while (it.hasNext()) {
            System.out.println("Текущий элемент - " + it.next());
        }
        Iterator<String> iterator = listOf.listIterator();
        while (iterator.hasNext()) {
            System.out.println("Текущий элемент - " + iterator.next());
        }
        Iterator<String> listIt = listOf.listIterator(1);
        while (listIt.hasNext()) {
            System.out.println("Текущий элемент.. - " + listIt.next());
        }
        System.out.println("ИЗМЕНЕНИЕ: ");
        List<String> list3 = new ArrayList<>();
        list3.add("one");
        list3.add("two");
        list3.add("three");
        list3.set(1, "second and two");
        Iterator<String> iter = list3.iterator();
        while (iter.hasNext()) {
            System.out.println("Текущий элемент - " + iter.next());
        }
        list3.replaceAll(String::toUpperCase);
        Iterator<String> iter1 = list3.iterator();
        while (iter1.hasNext()) {
            System.out.println("Текущий элемент - " + iter1.next());
        }
        System.out.println("УДАЛЕНИЕ: ");
        List<String> delist = new ArrayList<>();
        delist.add("one");
        delist.add("two");
        delist.add("three");
        delist.add("four");
        delist.add("five");
        delist.add("six");
        delist.remove(5);
        delist.remove("five");
        List<String> list4 = List.of("four");
        delist.removeAll(list4);
        List<String> list5 = List.of("one", "two");
        delist.retainAll(list5);
        delist.removeIf(s -> s.equals("two"));
        Iterator<String> iter2 = delist.iterator();
        while (iter2.hasNext()) {
            System.out.println("Оставшийся - " + iter2.next());
        }
        System.out.println("ПОЛЕЗНОЕ: ");
        List<String> indexlist = new ArrayList<>();
        indexlist.add("Consta");
        indexlist.add("Code");
        indexlist.add("Concurrent");
        indexlist.add("Consta");
        int i = indexlist.lastIndexOf("Consta");
        System.out.println("Index of E: " + i);
        List<String> sublist = indexlist.subList(0, 3);
        Iterator<String> subit = sublist.iterator();
        while (subit.hasNext()) {
            System.out.println("Sublist: " + subit.next());
        }
    }
}
