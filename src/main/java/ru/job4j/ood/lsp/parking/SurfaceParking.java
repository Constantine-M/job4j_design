package ru.job4j.ood.lsp.parking;

import java.util.List;

public class SurfaceParking implements Parking {

    private int size;

    public SurfaceParking(int size) {
        this.size = size;
    }

    @Override
    public boolean park(List<Transport> vehicles) {
        return false;
    }
}
