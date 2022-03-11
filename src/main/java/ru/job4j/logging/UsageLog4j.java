package ru.job4j.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

/**
 * 1. Log4j. Логирование системы.
 *
 * Данный класс описывает прицип
 * логирования сообщений.
 *
 * Здесь мы используем ПАКЕТ
 * {@link org.apache.log4j.Logger},
 * а не стандартный (который встроен
 * в Java). Поэтому туть внимательней.
 *
 * @author Constantine on 09.03.2022
 */
public class UsageLog4j {

    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warning message");
        LOG.error("error message");
    }
}
