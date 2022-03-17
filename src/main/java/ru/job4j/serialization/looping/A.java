package ru.job4j.serialization.looping;

import org.json.JSONPropertyIgnore;

/**
 * 5. Преобразование JSON в POJO. JsonObject.
 *
 * Данный класс создан для демонстрации
 * рекурсивного зацикливания.
 *
 * 1.Объект A содержит ссылку на объект B,
 * а он в свою очередь ссылается на
 * первоначальный объект A. При
 * выполнении будут осуществляться
 * переходы по ссылке и сериализация
 * до возникновения исключения
 * {@link StackOverflowError}.
 *
 * @author Constantine on 17.03.2022
 */
public class A {

    private B b;

    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }
}
