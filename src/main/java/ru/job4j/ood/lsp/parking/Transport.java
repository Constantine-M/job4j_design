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

    private String model;

    private int size = 1;

    public Transport(String model, int size) {
        this.model = model;
        this.size = size;
    }

    public Transport(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public int getSize() {
        return size;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
