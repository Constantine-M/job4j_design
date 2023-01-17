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
     * место легковой в дополнение.
     * После этого выясняем, сколько
     * парковочных мест мы заняли у
     * грузовых и легковых авто и
     * уменьшаем счетчики.
     * @param vehicle паркуемый транспорт
     * @return true, если транспорт
     * был успешно припаркован.
     */
    @Override
    public boolean park(Transport vehicle) {
        boolean result = false;
        int size = vehicle.getSize();
        if (size == 1 && size <= carParkingSpots) {
            cars.add(vehicle);
            carParkingSpots -= 1;
            result = true;
        } else if (size > 1 && size <= truckParkingSpots) {
            trucks.add(vehicle);
            truckParkingSpots -= size;
            result = true;
        } else if (size > truckParkingSpots
                    && size <= truckParkingSpots + carParkingSpots) {
            int truckSpotsOccupied = size - truckParkingSpots;
            int carSpotsOccupied = size - carParkingSpots;
            truckParkingSpots -= truckSpotsOccupied;
            carParkingSpots -= carSpotsOccupied;
            result = true;
        }
        return result;
    }
}
