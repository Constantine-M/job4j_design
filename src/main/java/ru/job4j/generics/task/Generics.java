package ru.job4j.generics.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 0. Что такое обобщенные типы (generics).
 * @author Constantine on 26.10.2021
 */
public class Generics {
    /**
     * Строки закомментированы специально,
     * в учебных целях для выполнения задания.
     */
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("Consta", 12));
        second.add(new Predator("Barsik", 5, 120));
        third.add(new Tiger("Ame", 7, 90, "Amur"));
        System.out.println("Без ограничений: ");
        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println("Ограничение сверху: ");
        /*gen.printBoundedWildCard(first);*/
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println("Ограничение снизу: ");
        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        /*gen.printLowerBoundedWildCard(third);*/
    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
