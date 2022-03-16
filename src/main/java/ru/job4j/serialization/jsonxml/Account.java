package ru.job4j.serialization.jsonxml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 2. Формат JSON.
 * 4. JAXB. Преобразование XML в POJO.
 *
 * Данный класс описывает счета
 * банка {@link Bank}.
 *
 * Для использования JAXB нам нужно
 * в модели добавить дефолтные
 * конструкторы. С полей для
 * простоты также уберем final.
 *
 * 1."JAXB.. может ругаться на
 * отсутствие пустого (без параметров)
 * конструктора. Поэтому не забываем
 * его прописывать. И прописывать
 * нужно вперёд всех остальных
 * конструкторов."
 *
 * 2.Для того чтобы сериализовать и
 * десериализовать нам нужно добавить
 * аннотации JAXB, которая даст
 * библиотеке информацию о том
 * как парсить объект.
 *
 * @author Constantine on 15.03.2022
 */
@XmlRootElement(name = "account")
public class Account {

    @XmlAttribute
    private int number;

    public Account() { }

    public Account(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Account{"
                + "number=" + number
                + '}';
    }
}
