package ru.job4j.ood.lsp.storegoods.control;

import java.util.Calendar;

/**
 * Данный класс описывает реализацию
 * интерфейса с использованием
 * класса {@link Calendar}.
 */
public class CalendarExpirationCalculator implements ExpirationCalculator<Calendar> {

    private final static long DAY_IN_MILLISECONDS = 86400000;

    /**
     * Данный метод показывает,
     * на сколько процентов израсходован
     * срок годности.
     * Если текущая дата больше, чем
     * дата окончания срока годности, то
     * результат = 100% (товар испорчен).
     * @param startDate дата создания товара.
     * @param endDate дата окончания срока годности.
     * @return расход срока годности в %.
     */
    @Override
    public double calculateInPercent(Calendar startDate, Calendar endDate) {
        Calendar currentDateTime = Calendar.getInstance();
        double result = 100;
        if (currentDateTime.compareTo(endDate) < 0) {
            long diffCreatedAndExp = diffInDays(startDate, endDate);
            long diffCreatedAndCurrent = diffInDays(startDate, currentDateTime);
            result = ((double) diffCreatedAndCurrent / (double) diffCreatedAndExp) * 100;
        }
        return result;
    }

    /**
     * Данный метод показывает разницу
     * в днях между датами.
     * @param first меньшая дата.
     * @param second большая дата.
     * @return разница в днях.
     */
    private long diffInDays(Calendar first, Calendar second) {
        return Math.abs(second.getTimeInMillis() - first.getTimeInMillis()) / DAY_IN_MILLISECONDS;
    }
}
