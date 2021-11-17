package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Создать модель User.
 * 2. Без переопределения equals и hashCode.
 *
 * @author Constantine on 15.11.2021
 */
public class User {

    private String name;

    private int children;

    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * 1.Если вы не переопределите hashcode(), то реализация
     * по умолчанию в классе объектов будет использоваться
     * коллекциями. Эта реализация дает разные значения для
     * разных объектов, даже если они равны в соответствии
     * с методом equals().
     *
     * ЕСЛИ НИЧЕГО НЕ ПЕРЕОПРЕДЕЛЕНО.
     *
     * После нескольких прогонов жуком увидел:
     * 1.Значение hash у ключей менялся периодически.
     * Первый раз - hash(first) = 1650958812, index = 12;
     *              hash(second) = 87283849, index = 9.
     * Второй раз - hash(first) = 824322752, index = 0;
     *              hash(second) = 931003769, index = 9.
     * 2.Элементы попадали всегда в разные бакеты.
     * 3.Метод {@code eauqls()} не вызывался, так как
     * сперва идет сравнение по hascode. Если они разные,
     * то сравнение по {@code eauqls()} пропускается.
     *
     * Если хеш-коды разные, то и входные объекты
     * ГАРАНТИРОВАННО разные!
     *
     * Если хеш-коды равны, то входные объекты
     * НЕ ВСЕГДА РАВНЫ.
     */
    public static void main(String[] args) {
        User first = new User("Consta", 0, new GregorianCalendar(1995, 3, 6));
        User second = new User("Consta", 0, new GregorianCalendar(1995, 3, 6));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " <- key : value -> " + entry.getValue());
        }
    }
}
