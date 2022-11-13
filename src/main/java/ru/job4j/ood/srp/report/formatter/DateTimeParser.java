package ru.job4j.ood.srp.report.formatter;

/**
 * Данный интерфейс описывает
 * парсер даты (дата/время).
 * @param <T> интерфейс,
 *           с которым будем работать.
 */
public interface DateTimeParser<T> {

    String parse(T t);
}
