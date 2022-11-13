package ru.job4j.ood.srp.report.currency;

public class InMemoryCurrencyConverter implements CurrencyConverter {

    private static final int CURRENCIES_COUNT = Currency.values().length;

    private final double[][] conversationTable = new double[CURRENCIES_COUNT][CURRENCIES_COUNT];

    /**
     * В данном конструкторе значения
     * нам нужно заполнить вручную.
     * Это есть таблица соотношения валют.
     * Например, отношение RUB к USD = 0.016.
     */
    public InMemoryCurrencyConverter() {
        this.conversationTable[Currency.RUB.ordinal()][Currency.USD.ordinal()] = 0.0162;
        this.conversationTable[Currency.RUB.ordinal()][Currency.EUR.ordinal()] = 0.0166;
        this.conversationTable[Currency.USD.ordinal()][Currency.EUR.ordinal()] = 1.0218;
        this.conversationTable[Currency.USD.ordinal()][Currency.RUB.ordinal()] = 62D;
        this.conversationTable[Currency.EUR.ordinal()][Currency.USD.ordinal()] = 0.9786;
        this.conversationTable[Currency.EUR.ordinal()][Currency.RUB.ordinal()] = 61.49D;
    }

    /**
     * Данный метод описывает конвертацию
     * валюты.
     * @param source из какой валюты конвертируем.
     * @param sourceValue кол-во денежных средств.
     * @param target в какую валюту конвертируем.
     * @return результат конвертации валют.
     */
    @Override
    public double convert(Currency source, double sourceValue, Currency target) {
        return sourceValue * conversationTable[source.ordinal()][target.ordinal()];
    }
}
