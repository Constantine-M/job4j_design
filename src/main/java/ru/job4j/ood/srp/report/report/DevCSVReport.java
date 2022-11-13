package ru.job4j.ood.srp.report.report;

import ru.job4j.ood.srp.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.store.Store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class DevCSVReport implements Report {

    private final Store store;

    private final DateTimeParser<Calendar> dateTimeParser;

    private final String target;

    public DevCSVReport(Store store, DateTimeParser<Calendar> dateTimeParser, String target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.target = target;
    }

    /**
     * Данный метод генерирует отчет
     * в виде строки. Согласно
     * требованию, должен сгенерироваться
     * отчет в виде CSV файла, что
     * не является возможным на мой
     * взгляд.
     * @param filter условие выборки
     *               сотрудников.
     * @return отчет в виде строки.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        List<String> outList = new ArrayList<>();
        String head = "Name;Hired;Fired;Salary;";
        outList.add(head);
        for (Employee employee : store.findBy(filter)) {
            StringBuilder text = new StringBuilder();
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary());
            outList.add(text.toString());
        }
        return null;
    }

    /**
     * Данный метод должен записывать
     * данные в файл CSV. Для этого
     * ему на вход нужен список
     * объединенных строк {@link StringBuilder}.
     * Из метода {@link DevCSVReport#generate}
     * мы не сможем вернуть такой
     * список.
     *
     * Нам нужен:
     * Name;Hired;Fired;Salary; - 1-я строка
     * CJ;13:11:2022 13:00;13:11:2022 13:00;100.1 - 2-я строка
     * А сможем получить:
     * Name;Hired;Fired;Salary;CJ;13:11:2022 13:00;13:11:2022 13:00;100.1
     *
     * То есть нам придется его еще
     * как-то точно разделить. Либо дважды
     * проделать одну и ту же работу -
     * пройтись циклом по работникам
     * и снова с помощью {@link StringBuilder}
     * объединить строки так как нам нужно.
     * @param outList список объединенных строк.
     * @throws IOException
     */
    private void outputData(List<String> outList) throws IOException {
        Path out = Paths.get(target);
        Files.write(out, outList);
    }
}
