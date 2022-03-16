package ru.job4j.serialization.jsonxml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

/**
 * 2. Формат JSON.
 * 4. JAXB. Преобразование XML в POJO.
 *
 * Данный класс описывает банк.
 * У него есть имя, капитализация,
 * список услуг, счета. И он либо
 * коммерческий, либо нет.
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
 * 2.1 @XmlRootElement - ставить
 * над сущностью, которая
 * будет корневой.
 *
 * 2.2 @XmlElement - поставить
 * над вложенными сущностями.
 *
 * 2.3 @XmlAttribute - поставить
 * для того чтобы поле
 * считалось атрибутом.
 *
 * 2.4 @XmlAccessType - указывает
 * как мы хотим читать/писать объект.
 * Мы выбрали доступ по ПОЛЯМ.
 *
 * @author Constantine on 15.03.2022
 */
@XmlRootElement(name = "bank")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bank {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private boolean commercial;

    @XmlAttribute
    private int capitalization;

    @XmlElement
    private Account account;

    @XmlElementWrapper
    @XmlElement(name = "service")
    private String[] services;

    public Bank() { }

    public Bank(String name, boolean commercial, int capitalization, Account account, String[] services) {
        this.name = name;
        this.commercial = commercial;
        this.capitalization = capitalization;
        this.account = account;
        this.services = services;
    }

    @Override
    public String toString() {
        return "Bank{"
                + "name='" + name + '\''
                + ", commercial=" + commercial
                + ", capitalization=" + capitalization
                + ", account=" + account
                + ", services=" + Arrays.toString(services)
                + '}';
    }
}
