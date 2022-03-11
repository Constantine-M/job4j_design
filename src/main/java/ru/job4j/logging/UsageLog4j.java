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
 * https://refactoring.guru/ru/design-patterns/facade
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
    }
}
