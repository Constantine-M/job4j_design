package ru.job4j.ood.srp.report.model;

import javax.xml.bind.annotation.*;
import java.util.Calendar;
import java.util.Objects;

/**
 * Данная модель описывает сотрудника
 * компании.
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
 */
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private Calendar hired;

    @XmlAttribute
    private Calendar fired;

    @XmlAttribute
    private double salary;

    public Employee() { }

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
