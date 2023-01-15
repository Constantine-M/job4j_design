package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

/**
 * Тестирую следующим образом:
 * 1.Создал парковку определенного размера.
 * 2.Создал список машин.
 * 3.У каждой машины есть марка/бренд/название.
 * 4.У легковых размер по умолчанию
 * должен быть = 1.
 * 5.У грузовых машин размер указываем.
 * 6.Скармливаем список машин
 * методу {@link Parking#park(List)}.
 * 7.Если не поместились -----> false.
 */
@Disabled
class ParkingTest {

    @Test
    void whenAllVehiclesParked() {
        Parking parking = new SurfaceParking(5);
        List<Transport> vehicles = List.of(
                new Car("Mersedes"),
                new Car("Benz"),
                new Truck("MAN", int 3)
        );
        assertThat(parking.park(vehicles)).isTrue();
    }

    @Test
    void whenParkedOnlyCars() {
        Parking parking = new SurfaceParking( 5);
        List<Transport> vehicles = List.of(
                new Car("Mersedes"),
                new Car("Benz"),
                new Car("E-class"),
                new Car("Lada"),
                new Car("Granta")
        );
        assertThat(parking.park(vehicles)).isTrue();
    }

    @Test
    void whenParkedOnlyTrucks() {
        Parking parking = new SurfaceParking(20);
        List<Transport> vehicles = List.of(
                new Car("Scania", int 4),
                new Car("MAN", int 5),
                new Car("Volvo", int 2),
                new Car("International", int 6),
                new Car("Sterling", int 3)
        );
        assertThat(parking.park(vehicles)).isTrue();
    }

    @Test
    void whenVehiclesCantParked() {
        Parking parking = new SurfaceParking(6);
        List<Transport> vehicles = List.of(
                new Car("Lamborghini"),
                new Car("Diablo"),
                new Truck("International", int 5)
        );
        assertThat(parking.park(vehicles)).isFalse();
    }
}