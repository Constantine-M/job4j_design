package ru.job4j.ood.srp.report.report;

import ru.job4j.ood.srp.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class DevCSVReport implements Report {

    private final Store store;

    private final DateTimeParser<Calendar> dateTimeParser;

    public DevCSVReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    /**
     * Данный метод генерирует отчет
     * в формате CSV. Это значит, что
     * в конце должен быть символ ";".
     *
     * Пробелы все убраны. Т.к. этот
     * отчет будет использоваться при
     * формировании файла, то проблемы
     * не нужны.
     * @param filter условие выборки
     *               сотрудников.
     * @return отчет в виде строки.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
