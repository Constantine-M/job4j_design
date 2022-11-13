package ru.job4j.ood.srp.report.currency;

public interface CurrencyConverter {

    /**
     * Данный метод описывает конвертацию
     * валюты.
     * @param source из какой валюты конвертируем.
     * @param sourceValue кол-во денежных средств.
     * @param target в какую валюту конвертируем.
     * @return результат конвертации валют.
     */
    double convert(Currency source, double sourceValue, Currency target);
}
