package ru.job4j.ood.template;

import java.util.Map;

/**
 * Данный интерфейс описывает
 * генератор шаблона.
 *
 * В шаблоне будут ключи.
 * Эти ключи = ключам внутри карты.
 * В результате вместо ключей будут
 * подставлены значения из карты,
 * и мы получим готовый шаблон.
 */
public interface Generator {

    String produce(String template, Map<String, String> args);
}
