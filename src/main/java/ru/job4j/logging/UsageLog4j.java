package ru.job4j.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1. Log4j. Логирование системы.
 * 2. Simple Loggin Facade 4 Java.
 *
 * Данный класс описывает прицип
 * логирования сообщений.
 *
 * Здесь мы используем ПАКЕТ
 * {@link org.apache.log4j.Logger},
 * а не стандартный (который встроен
 * в Java). Поэтому туть внимательней.
 *
 * Далее представим, что на проекте
 * 2 команды используют разные библиотеки
 * для логгирования сообщений.
 * 1 - исп-ют logback.
 * 2 - исп-ют log4j.
 * 2-ой команде придется настроить и log4j,
 * и logback, чтобы логгирование
 * работало во всей системе.
 *
 * Если команды будут использовать
 * прослойку slf4j перед своими
 * системами логгирования, то такой
 * проблемы не возникнет.
 *
 * SLF использует шаблон проектирования
 * - фасад. Шаблон фасад упрощает АПИ
 * логгеров. Почитать тут:
 * <a href="https://refactoring.guru/ru/design-patterns/facade"></a>
 *
 * Оператор плюс (+) для String создает
 * в памяти новую строку. Это плохо,
 * потому что в памяти создаются
 * копии строк.
 *
 * Чтобы избежать сложение строк
 * в slf4j используется шаблон
 * для подстановки переменных.
 *
 * Ниже пример:
 * {@code void debug(String var1, Object var2, Object var3);}
 * Первый параметр метода - это
 * шаблон. Шаблон содержит текст
 * и отметки, которые заменяются
 * на параметры.
 *
 *
 * @author Constantine on 11.03.2022
 */
public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warning message");
        LOG.error("error message");
        System.out.print(System.lineSeparator());
        String name = "Consta Mezenin";
        int age = 256;
        boolean married = false;
        char className = 'A';
        byte cache = 127;
        short sell = 3446;
        long income = 475668345;
        float cpuPower = 3.5f;
        double damage = 8549684.1549;

        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("Name : {}, Married : {}", name, married);
        LOG.debug("User age : {}, His income : {}", age, income);
        LOG.debug("CPU power is : {}, Mark : {}", cpuPower, className);
        LOG.debug("Game character : {}, Damage : {}", name, damage);
        LOG.debug("L2 cache(Mb) : {}, Mark : {}", cache, className);
        LOG.debug("CPU power is : {}, Married : {}", cpuPower, married);
        LOG.debug("Summary income : {}, Married : {}", income, married);
        LOG.debug("Sell order closed with (USD) : {}, Profit : {}", sell, income);
    }
}
