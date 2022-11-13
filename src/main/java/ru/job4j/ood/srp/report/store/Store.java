package ru.job4j.ood.srp.report.store;

import ru.job4j.ood.srp.report.model.Employee;

import java.util.List;
import java.util.function.Predicate;

/**
 * Данный интерфейс описывает
 * хранилище, куда мы будем добавлять
 * сотрудников. И из этого же
 * хранилища забирать.
 */
public interface Store {

    void add(Employee em);

    List<Employee> findBy(Predicate<Employee> filter);
}
