package ru.job4j.ood.srp.report.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.report.currency.Currency;
import ru.job4j.ood.srp.report.currency.CurrencyConverter;
import ru.job4j.ood.srp.report.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.report.formatter.XMLDateTimeParser;
import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

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

    /**
     * Данный тест показывает, что отчет
     * сгенерировался в формате XML.
     * 1.Написал новый парсер {@link XMLDateTimeParser},
     * потому что не удалось привести
     * время к единому значению с уже
     * имеющимися структурами.
     * 2.Пришлось выставить время вручную,
     * без указания секунд,
     * т.к. тест не проходился из-за
     * разницы в долях секунды.
     * 3.{@link  System#lineSeparator()}
     * пришлось заменить на "\n",
     * иначе тест падал.
     * @throws JAXBException
     */
    @Test
    public void whenXMLReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = new GregorianCalendar();
        date.set(Calendar.YEAR, 2022);
        date.set(Calendar.MONTH, 10);
        date.set(Calendar.DAY_OF_MONTH, 22);
        date.set(Calendar.HOUR, 13);
        date.set(Calendar.MINUTE, 53);
        DateTimeParser<Calendar> parser = new XMLDateTimeParser();
        Employee cj = new Employee("CJ", date, date, 100);
        Employee consta = new Employee("Consta", date, date, 130.1);
        store.add(cj);
        store.add(consta);
        Report engine = new XMLReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n")
                .append("<employee name=")
                .append("\"").append(cj.getName()).append("\"").append(" ")
                .append("hired=").append("\"").append(parser.parse(date)).append("\"").append(" ")
                .append("fired=").append("\"").append(parser.parse(date)).append("\"").append(" ")
                .append("salary=").append("\"").append(cj.getSalary()).append("\"").append("/>")
                .append("\n")
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n")
                .append("<employee name=")
                .append("\"").append(consta.getName()).append("\"").append(" ")
                .append("hired=").append("\"").append(parser.parse(date)).append("\"").append(" ")
                .append("fired=").append("\"").append(parser.parse(date)).append("\"").append(" ")
                .append("salary=").append("\"").append(consta.getSalary()).append("\"").append("/>")
                .append("\n");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    /**
     * Данный тест показывает, что
     * тест сгенерировался в формате JSON.
     * Вдобавок к комментарию выше,
     * здесь есть интересная вещь:
     * если зарплата целочисленная,
     * то сериализуется целое
     * число, которое будет сравниваться
     * с дробным (100 сравнивается 100.0).
     * Нужно уже в тесте решить, какая будет
     * зарплата у обоих пользователей.
     */
    @Test
    public void whenJSONReportGenerated() {
        MemStore store = new MemStore();
        Calendar date = new GregorianCalendar();
        date.set(Calendar.YEAR, 2022);
        date.set(Calendar.MONTH, 10);
        date.set(Calendar.DAY_OF_MONTH, 22);
        date.set(Calendar.HOUR, 13);
        date.set(Calendar.MINUTE, 53);
        DateTimeParser<Calendar> parser = new XMLDateTimeParser();
        Employee cj = new Employee("CJ", date, date, 100.5);
        Employee sweet = new Employee("Sweet", date, date, 130.1);
        store.add(cj);
        store.add(sweet);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder()
                .append("{").append("\"").append("fired").append("\"").append(":")
                .append("\"").append(cj.getFired().getTime()).append("\"").append(",")
                .append("\"").append("name").append("\"").append(":")
                .append("\"").append(cj.getName()).append("\"").append(",")
                .append("\"").append("hired").append("\"").append(":")
                .append("\"").append(cj.getHired().getTime()).append("\"").append(",")
                .append("\"").append("salary").append("\"").append(":")
                .append(cj.getSalary()).append("}")
                .append(System.lineSeparator())
                .append("{").append("\"").append("fired").append("\"").append(":")
                .append("\"").append(sweet.getFired().getTime()).append("\"").append(",")
                .append("\"").append("name").append("\"").append(":")
                .append("\"").append(sweet.getName()).append("\"").append(",")
                .append("\"").append("hired").append("\"").append(":")
                .append("\"").append(sweet.getHired().getTime()).append("\"").append(",")
                .append("\"").append("salary").append("\"").append(":")
                .append(sweet.getSalary()).append("}")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}