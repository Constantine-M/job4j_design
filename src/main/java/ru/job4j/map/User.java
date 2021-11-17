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

    /*@Override
    public int hashCode() {
        return super.hashCode();
    }*/

    /**
     * 1.Если вы не переопределите hashcode(), то реализация
     * по умолчанию в классе объектов будет использоваться
     * коллекциями. Эта реализация дает разные значения для
     * разных объектов, даже если они равны в соответствии
     * с методом equals().
     *
     * Если хеш-коды разные, то и входные объекты
     * ГАРАНТИРОВАННО разные!
     * Если хеш-коды равны, то входные объекты
     * НЕ ВСЕГДА РАВНЫ!
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
     * 3.Метод {@code equals()} не вызывался, так как
     * сперва идет сравнение по hashcode. Если они разные,
     * то сравнение по {@code equals()} пропускается.
     *
     * ЕСЛИ ПЕРЕОПРЕДЕЛЕН ТОЛЬКО HASHCODE().
     *
     * После нескольких прогонов (5 times) жуком увидел:
     * 1.Значение hash у ключей менялся периодически.
     * Первый прогон - hash(first) = 1650958812, index = 12;
     *                 hash(second) = 87283849, index = 9.
     * Третий прогон - hash(first) = 824322752, index = 0;
     *                 hash(second) = 931003769, index = 9.
     * 2.Элементы попадали всегда в разные бакеты.
     * 3.Метод {@code equals()} не вызывался, так как
     * сперва идет сравнение по hashcode. Если они разные,
     * то сравнение по {@code equals()} пропускается.
     * Пока не понятно, использовался ли вообще метод
     * {@code hashCode()}, т.к. IDE ругается.
     */
    public static void main(String[] args) {
        User first = new User("Consta", 0, new GregorianCalendar(1995, Calendar.MARCH, 6));
        User second = new User("Consta", 0, new GregorianCalendar(1995, Calendar.MARCH, 6));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey().hashCode() + " <- key : value -> " + entry.getValue());
        }
    }
}
