package ru.job4j.ood.srp.report.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import ru.job4j.ood.srp.report.model.Employee;
import ru.job4j.ood.srp.report.model.Employees;
import ru.job4j.ood.srp.report.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private final Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    /**
     * Данный метод генерирует отчет в
     * формате JSON.
     *
     * Сериализацию можно выполнить 3 способами:
     * 1.JSONObject из json-строки строки.
     * 2.JSONArray из ArrayList.
     * 3.JSONObject напрямую методом put.
     * Я сделал 3-им способом.
     * @param filter условие выборки сотрудников.
     * @return очтет в формате JSON.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(filter));
        Gson lib = new GsonBuilder().create();
        return lib.toJson(employees);
    }

    /**
     * Данный метод проходит по списку
     * JSON объектов и объединяет
     * их в объект {@link StringBuilder}.
     * Вынес этот блок в отдельный метод.
     * @param jsonObjectList список объектов JSON.
     * @return объект {@link StringBuilder}.
     */
    private StringBuilder appendJsonsFromList(List<JSONObject> jsonObjectList) {
        StringBuilder result = new StringBuilder();
        for (JSONObject object : jsonObjectList) {
            result.append(object.toString())
                    .append(System.lineSeparator());
        }
        return result;
    }
}
