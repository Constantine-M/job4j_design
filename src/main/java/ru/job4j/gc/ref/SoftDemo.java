package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 0. Виды ссылок.
 *
 * Данный класс описывает
 * принцип работы soft reference.
 * Это  некотором смысле
 * безопасные ссылки.
 *
 * Объекты, на которые ссылаются безопасные
 * ссылки, удаляются только если JVM не
 * хватает памяти, т.е. они могут пережить
 * более одной сборки мусора.
 *
 * Данный тип ссылок подходит для реализации
 * кэша - такой структуры данных, при
 * которой часть данных запоминается, а
 * потом часто переиспользуется.
 *
 * Есть контракт для данного типа ссылок:
 * GC гарантировано удалит с кучи все
 * объекты, доступные только по soft-ссылке,
 * перед тем как бросит {@link OutOfMemoryError}.
 *
 * @author Constantine on 25.08.2022
 */
public class SoftDemo {

    private static final String CODING = "do something";

    public static void main(String[] args) {
        example1();
        example2();
    }

    /**
     * В данном методе не смотря на то, что
     * мы за'null'уляем сильную ссылку,
     * на объект остается безопасная ссылка,
     * а это значит объект будет удален
     * только при нехватке памяти.
     */
    private static void example1() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        System.out.println(soft.get());
    }

    /**
     * В данном методе мы создаем много
     * объектов, но на них ссылается
     * безопасная ссылка. Если мы при
     * создании большое количество объектов
     * при малом хипе, мы увидим, что объекты
     * начнут удаляться, т.к. станет
     * не хватать памяти.
     *
     * С кол-вом в 100млн это можно увидеть.
     * А с кол-вом, например, 1 млн
     * можно увидеть, что все ссылки
     * сохранились.
     */
    private static void example2() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            objects.add(new SoftReference<Object>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }

    private static void unsafe() {
        List<SoftReference<Object>> someData = new ArrayList<>();
        if (someData.get(0).get() != null) {
            System.out.println(CODING);
        } else {
            System.out.println(CODING);
        }
        System.out.println(CODING);
        someData.get(0).get();
    }

    /**
     * Данный метод показыает, как
     * следует работать с soft reference.
     *
     * "Корректным использованием безопасных
     * ссылок является сначала получение
     * сильной ссылки на данные, а потом
     * работа с сильной ссылкой.
     *
     * Это гарантирует, что в интервалах
     * получения сильной ссылки из безопасной
     * GC не затрет объект. Это касается не
     * только локальных переменных, но и
     * возвращаемых значений и аргументов."
     */
    private static void safe() {
        List<SoftReference<Object>> someData = new ArrayList<>();
        Object strong = someData.get(0).get();
        if (strong != null) {
            System.out.println(CODING);
        } else {
            System.out.println(CODING);
        }
        /*WORK WITH STRONG REF*/
    }
}
