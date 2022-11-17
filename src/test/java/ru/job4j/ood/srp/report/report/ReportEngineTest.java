package ru.job4j.ood.srp.report.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.report.currency.Currency;
import ru.job4j.ood.srp.report.currency.CurrencyConverter;
import ru.job4j.ood.srp.report.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.store.MemStore;

import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.*;

class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    /**
     * Тест должен показать, что зарплата
     * сотрудника была переконвертирована
     * в доллары.
     */
    @Test
    public void whenAccountingReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker = new Employee("CJ", now, now, 170000);
        store.add(worker);
        Report engine = new AccountingReport(store, parser, converter, Currency.USD);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(2754D)
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    /**
     * Данный тест должен показать,
     * что сотрудники сортируются по
     * зарплатам (от большей к меньшей).
     */
    @Test
    public void whenHumanResourcesReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Comparator<Employee> comparator = Comparator.comparingDouble(Employee::getSalary).reversed();
        Employee cj = new Employee("CJ", now, now, 170000);
        Employee sweet = new Employee("Sweet", now, now, 200000);
        Employee lance = new Employee("Lance", now, now, 350000);
        store.add(cj);
        store.add(sweet);
        store.add(lance);
        Report engine = new HumanResourcesReport(store, comparator);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(lance.getName()).append(" ")
                .append(lance.getSalary())
                .append(System.lineSeparator())
                .append(sweet.getName()).append(" ")
                .append(sweet.getSalary())
                .append(System.lineSeparator())
                .append(cj.getName()).append(" ")
                .append(cj.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    /**
     * Данный тест проверяет, что
     * отчет сгенерировался в формате CSV.
     */
    @Test
    public void whenDevReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee cj = new Employee("CJ", now, now, 100000.123);
        Employee sweet = new Employee("Sweet", now, now, 160000.456);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(cj);
        store.add(sweet);
        Report engine = new DevCSVReport(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator())
                .append(cj.getName()).append(";")
                .append(parser.parse(cj.getHired())).append(";")
                .append(parser.parse(cj.getFired())).append(";")
                .append(cj.getSalary())
                .append(System.lineSeparator())
                .append(sweet.getName()).append(";")
                .append(parser.parse(sweet.getHired())).append(";")
                .append(parser.parse(sweet.getFired())).append(";")
                .append(sweet.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}