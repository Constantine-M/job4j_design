package ru.job4j.ood.lsp.parking;

public class Truck extends Transport {

    public Truck(String model, int size) {
        super(model, size);
        if (validate(size)) {
            throw new IllegalArgumentException("Truck size must be > 1");
        }
    }

    private boolean validate(int spaceSize) {
        return spaceSize <= 1;
    }
}
