package ru.job4j.ood.lsp.storegoods.datetimeparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDateTimeParser implements DateTimeParser<Calendar> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-M-yyyy hh:mm");

    /**
     * Метод сделан специально для задачи.
     * Я посчитал нужным написать его
     * для облегчения тестирования.
     * @param data дата в формате "dd-M-yyyy hh:mm"
     * @return дату в формате {@link Calendar}
     */
    @Override
    public Calendar parse(String data) {
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = DATE_FORMAT.parse(data);
            calendar.setTime(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse string to date!", e);
        }
        return calendar;
    }
}
