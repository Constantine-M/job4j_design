package ru.job4j.serialization.jsonxml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 2. Формат JSON.
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
 * @author Constantine on 13.03.2022
 */
public class Main {
    public static void main(String[] args) {
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
    }
}
