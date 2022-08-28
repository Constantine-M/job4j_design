package ru.job4j.gc.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 0. Виды ссылок.
 *
 * Данный класс описывает принцип
 * работы сильных ссылок или
 * Strong Reference.
 *
 * @author Constantine on 25.08.2022
 */
public class StrongDemo {

    public static void main(String[] args) throws InterruptedException {
        example1();
        example2();
        example3();
    }

    /**
     * В данном методе мы создаем объект и далее
     * их за'null'яем. Вызываем сборщик мусора и
     * ждем некоторое время. Объекты удаляются,
     * т.к. ссылки на них = null.
     * @throws InterruptedException
     */
    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * В данном методе мы создаем объекты вместе с
     * вложенными. Удаляя внешние объекты как в
     * примере выше удаляются и вложенные,
     * которые InnerObject,
     * не смотря на то что они не null.
     * @throws InterruptedException
     */
    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object!");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Данный метод приведет к ошибке
     * {@link OutOfMemoryError},
     * потому что в этом примере не
     * будут удалены неисользуемые ссылки
     * на созданные объекты.
     */
    private static void example3() {
        List<String> strings = new ArrayList<>();
        while (true) {
            strings.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}
