package ru.job4j.ood.lsp.parking;

/**
 * Данный класс описывает грузовое авто.
 * Здесь мы несколько упростили код,
 * использовав глабальную константу,
 * которую прописали в {@link Transport}.
 */
public class Truck extends Transport {

    public Truck(String model, int size) {
        super(model, size);
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException("Truck size must be > 1");
        }
    }
}
