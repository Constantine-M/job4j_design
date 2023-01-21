package ru.job4j.ood.lsp.parking;

/**
 * Данный класс описывает
 * модель легкового авто.
 * Т.к. размер авто на парковке
 * фиксированный и = 1, то
 * определим константу именно здесь.
 * Каждая реализация может иметь
 * свой дефолтный размер, поэтому
 * размещение константы в классе
 * {@link Transport} было
 * неправильным.
 */
public class Car extends Transport {

    /**
     * Размер легкового авто
     * по умолчанию.
     */
    public final static int SIZE = 1;

    public Car(String model) {
        super(model);
    }
}
