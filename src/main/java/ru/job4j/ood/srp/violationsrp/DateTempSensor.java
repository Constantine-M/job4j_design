package ru.job4j.ood.srp.violationsrp;

import java.util.Calendar;

/**
 * В данном классе нарушается принцип SRP.
 * 1.Класс не зависит от абстракции.
 * 2.Поля можно вынести в отдельные
 * классы/интерфейсы.
 */
public class DateTempSensor {

    private Calendar date;

    private int temp;

    public void measure() {
        /*Код, в котором будет описана
        запись температуры и даты куда-нибудь.
        Например, в БД.*/
    }
}
