package ru.job4j.ood.lsp.storegoods.datetimeparser;

import java.text.ParseException;

/**
 * Данный интерфейс описывает
 * преобразование строки в
 * дату. При этом мы можем
 * возвращать результат как
 * в виде {@link java.util.Date},
 * так и в виде {@link java.util.Calendar}.
 * @param <T> формат преобразования даты.
 */
public interface DateTimeParser<T> {

    T parse(String data);
}
