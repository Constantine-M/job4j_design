package ru.job4j.serialization.jsonxml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 2. Формат JSON.
 * 4. JAXB. Преобразование XML в POJO.
 * 5. Преобразование JSON в POJO. JsonObject.
 *
 * Для работы с json мы использовали
 * библиотеку Gson. Она позволяет
 * преобразовывать json-строку
 * сразу в объект и наоборот.
 *
 * 1.Объявляем Gson, создаем
 * объект Gson.
 * 2.Преобразуем в json-строку
 * объект класса {@link Bank}.
 * 3.После этого получим 1 строку,
 * в которой все данные. Читать
 * неудобно..
 * 4.Модифицируем json-строку,
 * приводим в читабельный вид.
 *
 * Вариант с использованием XML.
 *
 * 1.Получаем контекст для доступа
 * к АПИ {@link JAXBContext}.
 * 2.Создаем сериализатор
 * {@link javax.xml.bind.Marshaller}.
 * 3.Указываем, что нам нужно
 * форматирование (JAXB_FORMATTED_OUTPUT).
 * 4.В блоке try производим сериализацию
 * {@link Marshaller#marshal(Object, File)}.
 *
 * standalone="yes" - обозначает,
 * что в файле XML имеется вложение
 * (вложенный класс в нашем примере).
 *
 * Преобразование POJO объекта
 * в объект {@link JSONObject}.
 *
 * Можно выполнить 3 способами:
 * 1.JSONObject из json-строки строки.
 * 2.JSONArray из ArrayList.
 * 3.JSONObject напрямую методом put.
 *
 *
 *
 * @author Constantine on 15.03.2022
 */
public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        final Bank bank = new Bank("JuniorsBank", true,
                564548551, new Account(7777777),
                new String[] {"deposit", "mortgage"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(bank));
        final String bankJson =
                "{"
                        + "\"name\":\"JuniorsBank\","
                        + "\"commercial\":true,"
                        + "\"capitalization\":564548551,"
                        + "\"account\":"
                        + "{"
                        + "\"number\":7777777"
                        + "},"
                        + "\"services\":"
                        + "[\"deposit\",\"mortgage\"]"
                        + "}";
        final Bank bankMod = gson.fromJson(bankJson, Bank.class);
        System.out.println(bankMod);
        System.out.println(System.lineSeparator());
        System.out.println("----- XML SERIALIZATION -----");
        JAXBContext context = JAXBContext.newInstance(Bank.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(bank, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        System.out.println("----- XML DESERIALIZATION -----");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Bank result = (Bank) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        System.out.println(System.lineSeparator());
        JSONObject account = new JSONObject("{\"number\":\"7777777\"}");
        List<String> list = new ArrayList<>();
        list.add("JuniorsBank");
        list.add("MiddlesBank");
        JSONArray services = new JSONArray(list);
        System.out.println("----- POJO to JSONObject EXAMPLE -----");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", bank.getName());
        jsonObject.put("commercial", bank.isCommercial());
        jsonObject.put("commercial", bank.isCommercial());
        jsonObject.put("capitalization", bank.getCapitalization());
        jsonObject.put("account", account);
        jsonObject.put("services", services);
        System.out.println(jsonObject.toString());
        System.out.println("----- AS A RESULT - BANK to JSON-string -----");
        System.out.println(new JSONObject(bank).toString());
    }
}
