package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

public class SurfaceParking implements Parking {

    private Set<Transport> cars = new HashSet<>();

    private Set<Transport> trucks = new HashSet<>();

    private int carParkingSpots;

    private int truckParkingSpots;

    public SurfaceParking(int carParkingSpots, int truckParkingSpots) {
        this.carParkingSpots = carParkingSpots;
        this.truckParkingSpots = truckParkingSpots;
    }

    /**
     * Данный метод паркует авто -
     * легковые и грузовые.
     * Узнаем по размеру, легковое
     * авто или грузовое.
     * 1.Если легкое и есть места для
     * легковых машин - ставим и
     * уменьшаем счетчик мест для
     * легковых машин.
     * 2.Если грузовое и есть места
     * для грузовых машин - ставим и
     * уменьшаем счетчик мест для
     * грузовых машин.
     * 3.Если грузовое авто и мест
     * для грузовых авто нет, то
     * проверяем, есть ли места для
     * легковых. Если да, то занимаем
     * места легковых машин в зависимости
     * от размера грузового авто.
     * После этого выясняем, сколько
     * парковочных мест мы заняли у
     * легковых авто и уменьшаем счетчик.
     * @param vehicle паркуемый транспорт
     * @return true, если транспорт
     * был успешно припаркован.
     */
    @Override
    public boolean park(Transport vehicle) {
        boolean result = false;
        int size = vehicle.getSize();
        if (size > Car.SIZE && truckParkingSpots > 0) {
            trucks.add(vehicle);
            truckParkingSpots -= Car.SIZE;
            result = true;
        } else if (size <= carParkingSpots) {
            cars.add(vehicle);
            carParkingSpots -= size;
            result = true;
        }
        return result;
    }
}
