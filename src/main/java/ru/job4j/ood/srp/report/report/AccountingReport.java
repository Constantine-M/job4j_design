package ru.job4j.ood.srp.report.report;

import ru.job4j.ood.srp.report.currency.Currency;
import ru.job4j.ood.srp.report.currency.CurrencyConverter;
import ru.job4j.ood.srp.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

/**
 * Данный класс описывает генерацию
 * бухгалтерского отчета согласно
 * требованиям ниже:
 * 1.В бухгалтерском отчете необходимо
 * сделать конвертацию зарплаты в одну из валют.
 */
public class AccountingReport implements Report {

    private final Store store;

    private final DateTimeParser<Calendar> dateTimeParser;

    private CurrencyConverter converter;

    private final Currency target;

    /**
     * Добавил в конструктор
     * {@link CurrencyConverter}. Без
     * него не происходила конвертация.
     * @param store хранилище.
     * @param dateTimeParser парсер даты.
     *                       Переводит дату и время в
     *                       удобный формат.
     * @param converter конвертер валюты.
     * @param target конечная валюта,
     *               в которую конвертируем.
     */
    public AccountingReport(Store store, DateTimeParser<Calendar> dateTimeParser, CurrencyConverter converter, Currency target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(Currency.RUB, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
