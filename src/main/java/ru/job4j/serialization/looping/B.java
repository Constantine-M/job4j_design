package ru.job4j.serialization.looping;

import org.json.JSONObject;

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
 * 2.Чтобы избежать исключения
 * необходимо разорвать цепочку,
 * как правило это делается в момент
 * перехода по ссылке на объект,
 * который уже сериализован.
 *
 * Для этого существует аннотация
 * {@link org.json.JSONPropertyIgnore}.
 *
 * @author Constantine on 17.03.2022
 */
public class B {

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
        System.out.println(new JSONObject(b));
    }
}
