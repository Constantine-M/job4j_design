package ru.job4j.generics;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 0. Что такое обобщенные типы (generics).
 * @author Constantine on 25.10.2021
 */
public class GenericUsages {
    /**
     * 1.WildCard - обозначает <?>
     * ограничений нет, т.е. он имеет
     * соответствие с любым типом.
     * @param list список любой.
     */
    public void printRsl(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    /**
     * 2.Bounded Wildcard - <? extends Person>
     * Такой подход используется,
     * если метод который нужно реализовать использует
     * определенный тип и все его подтипы.
     * Это так называемое "ограничение сверху".
     * @param list список Person or Programmers.
     */
    public void printInfo(List<? extends Person> list) {
        for (Iterator<? extends Person> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    /**
     * 3.Lower bounded Wildcard - <? super A>.
     * Это ограниченный снизу wildcard.
     * Представим себе ситуацию, что мы хотим написать метод,
     * который помещает объекты Integer в список и выводит
     * этот список в консоль. При этом наш метод должен быть
     * более гибким и работал не только с типом Integer,
     * но и с его суперклассами (т.е. {@link Number} и {@link Object}).
     */
    public void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object o : list) {
            System.out.println("Текущий элемент: " + o);
        }
    }

    /**
     * Сочетание клавиш Ctrl + Alt + V
     * (навести на {@code list.get}
     * позволит извлечь переменную и присвоить тип.
     * Все классы в Java являются наследниками класса Object.
     * Компилятор не понимает, как мы можем
     * Object привести к типу String.
     * Спасет только downcasting.
     * Ключевые варианты реализации generics в Java.
     * Существует такое понятие, связанное с generics,
     * как НЕОБРАБОТАННЫЕ ТИПЫ (в литературе, интернете
     * еще можно встретить такое название как "сырые типы").
     * Обозначаются они так же как и generics в скобках <>.
     * ** Мы можем узнать актуальный параметр generic-класса,
     * если этот параметр был задан явным образом
     * (т.е. параметр определен внутри секции
     * extends одного из наследников).
     */
    public static void main(String[] args) {
        List list1 = new ArrayList();
        list1.add("first");
        list1.add("second");
        list1.add("third");
        list1.add(new Person("Consta", 12, new Date(913716000000L)));
        list1.get(1);
        Object o = list1.get(1);
        String o1 = (String) list1.get(1);
        new GenericUsages().printRsl(list1);
        System.out.println("Далее вторая реализация: ");
        List<Person> per = List.of(
                new Person("Consta", 12, new Date(913716000000L))
        );
        List<Programmer> prog = List.of(
                new Programmer("ConstaMez", 121, new Date(913716100000L))
        );
        new GenericUsages().printInfo(per);
        new GenericUsages().printInfo(prog);
        System.out.println("Далее третья реализация: ");
        List<Integer> listInt = new ArrayList<>();
        new GenericUsages().addAll(listInt);
        System.out.println("Актуальный параметр generic-класса: ");
        ArrayList<Float> listOfNumbers = new FloatList();
        Class actual = listOfNumbers.getClass();
        ParameterizedType type = (ParameterizedType) actual.getGenericSuperclass();
        System.out.println(type);
        Class parameter = (Class) type.getActualTypeArguments()[0];
        System.out.println(parameter);
    }
}
