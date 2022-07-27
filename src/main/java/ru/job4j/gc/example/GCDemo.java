package ru.job4j.gc.example;

/**
 * 0. Понятие сборки мусора.
 *
 * Данный класс показывает
 * принцип работы сборщика мусора (GC).
 *
 * @author Constantine on 24.07.2022
 */
public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    /**
     * Данный метод выводит в консоль
     * основную информацию, касающуюся
     * использования памяти.
     *
     * - freeMemory(), возвращает количество
     * свободной памяти в байтах.
     * - totalMemory(), возвращает общее
     * количество памяти которое может быть использовано
     * - maxMemory(), возвращает максимальное
     * количество памяти которое может быть использовано
     *
     * Мы создаем в цикле 10/100/1000 объектов.
     * Если запустить этот код, то можно
     * увидеть, что происходит очистка мусора.
     */
    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) throws InterruptedException {
        info();
        for (int i = 0; i < 10; i++) {
            new Person(i, "N" + i);
        }
        System.gc();
        Thread.sleep(20000);
        info();
    }
}
