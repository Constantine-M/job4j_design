package ru.job4j.ood.srp.report.report;

import ru.job4j.ood.srp.report.model.Employee;

import java.util.function.Predicate;

/**
 * Данный интерфейс описывает
 * отчет и его базовый функционал.
 */
public interface Report {

    String generate(Predicate<Employee> filter);
}
