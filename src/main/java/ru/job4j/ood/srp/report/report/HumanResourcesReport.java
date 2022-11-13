package ru.job4j.ood.srp.report.report;

import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Данный класс описывает
 * генерацию отчета для отдела кадров
 * согласно следующим требованиям.
 * 1.Выводить сотрудников в порядке
 * убывания зарплаты.
 * 2.Убрать поля даты найма и увольнения.
 */
public class HumanResourcesReport implements Report {

    private final Store store;

    private final Comparator<Employee> comparator;

    public HumanResourcesReport(Store store, Comparator<Employee> comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> servants = store.findBy(filter);
        servants.sort(comparator);
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : servants) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
