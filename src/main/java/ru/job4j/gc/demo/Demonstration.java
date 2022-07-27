package ru.job4j.gc.demo;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.List;

/**
 * 1. Демонстрация работы GC.
 *
 * @author Constantine on 24.07.2022
 */
public class Demonstration {

    private static final double KB = 1000;

    private static final double MB = KB * KB;

    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.println("IN MEGABYTE:");
        System.out.printf("Free: %f%n", freeMemory / MB);
        System.out.printf("Total: %f%n", totalMemory / MB);
        System.out.printf("Max: %f%n", maxMemory / MB);
        System.out.print(System.lineSeparator());
        System.out.println("IN BYTES:");
        System.out.printf("Free: %d%n", freeMemory);
        System.out.printf("Total: %d%n", totalMemory);
        System.out.printf("Max: %d%n", maxMemory);
        System.out.println("=========================");
    }

    /**
     * Если не вызывать сборщик вручную
     * (затереть строку вызова) и установить
     * ключ -Xmx4m, то сборщик вызовется
     * самостоятельно.
     */
    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10; i++) {
            new User(i, "user" + i, i + 1);
        }
        /*List<User> users = List.of(
                new User(12, "Consta", 73),
                new User(21, "Alex", 21),
                new User(43, "Lily", 99),
                new User(99, "Olga", 3),
                new User(54, "Jerry", 50),
                new User(32, "Gocha", 88),
                new User(81, "Anna", 23),
                new User(3, "Diana", 0),
                new User(6, "Veider", 1),
                new User(17, "Luke", 68)
        );
        users.forEach(System.out::println);*/
        System.out.println("RUNNING GARBAGE COLLECTOR...");
        System.out.print(System.lineSeparator());
        System.gc();
        info();
    }
}
