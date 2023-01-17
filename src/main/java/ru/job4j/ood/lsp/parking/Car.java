package ru.job4j.ood.lsp.parking;

/**
 * Данный класс описывает
 * модель легкового авто.
 * Т.к. размер авто на парковке
 * фиксированный и = 1, я попытался
 * сделать так, чтобы не прописывать
 * в явном виде размер, но
 * мне это пока не удалось.
 * Через константу пока никак
 * не сделать, но можно изменить
 * размер в родителе...
 */
public class Car extends Transport {

    private static final int SPACE_SIZE = 1;

    public Car(String model, int size) {
        super(model, size);
        setSize(SPACE_SIZE);
    }

    public Car(String model) {
        super(model);
    }
}
