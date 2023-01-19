package ru.job4j.ood.lsp.parking;

/**
 * Данный абстрактный класс описывает
 * транспорт в общем виде.
 * В этом классе я указал размер
 * авто по умолчанию = 1 и
 * добавил конструктор без
 * размера (поля размера), чтобы
 * можно было добавлять авто (легковое),
 * не указывая его размер.
 */
public abstract class Transport {

    public final static int DEFAULT_SIZE = 1;

    private String model;

    private int size = DEFAULT_SIZE;

    public Transport(String model, int size) {
        this.model = model;
        this.size = size;
    }

    public Transport(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }
}
