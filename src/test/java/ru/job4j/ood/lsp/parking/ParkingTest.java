package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

/**
 * Тестирую следующим образом:
 * 1.Создал парковку определенного размера.
 * 2.Добавляем машины по очереди.
 * 3.У каждой машины есть марка/бренд/название.
 * 4.У легковых размер по умолчанию
 * должен быть = 1.
 * 5.У грузовых машин размер указываем.
 * 6.Скармливаем список машин
 * методу {@link Parking#park}.
 * 7.Если размер совпадает, то все
 * машины припарковались.
 */
class ParkingTest {

    @Test
    void whenAllVehiclesParked() {
        Parking parking = new SurfaceParking(2, 3);
        List<Transport> vehicles = List.of(
                new Car("Mercedes"),
                new Car("Benz")
        );
        vehicles.forEach(parking::park);
        assertThat(parking.park(new Truck("MAN", 3))).isTrue();
    }

    @Test
    void whenParkedOnlyCars() {
        Parking parking = new SurfaceParking(5, 0);
        List<Transport> vehicles = List.of(
                new Car("Mersedes"),
                new Car("Benz"),
                new Car("E-class"),
                new Car("Lada")
        );
        vehicles.forEach(parking::park);
        assertThat(parking.park(new Car("Granta"))).isTrue();
    }

    @Test
    void whenParkedOnlyTrucks() {
        Parking parking = new SurfaceParking(6, 14);
        List<Transport> vehicles = List.of(
                new Truck("Scania", 4),
                new Truck("MAN", 5),
                new Truck("Volvo", 2),
                new Truck("International", 6)
        );
        vehicles.forEach(parking::park);
        assertThat(parking.park(new Truck("Sterling", 3))).isTrue();
    }

    @Test
    void whenTruckCantParked() {
        Parking parking = new SurfaceParking(2, 3);
        List<Transport> vehicles = List.of(
                new Car("Lamborghini"),
                new Car("Diablo")
        );
        vehicles.forEach(parking::park);
        assertThat(parking.park(new Truck("International", 5))).isFalse();
    }

    @Test
    void whenSecondCarCantParked() {
        Parking parking = new SurfaceParking(1, 3);
        List<Transport> vehicles = List.of(
                new Car("Vesta"),
                new Truck("Iveco", 3)
        );
        vehicles.forEach(parking::park);
        assertThat(parking.park(new Car("Ferrari"))).isFalse();
    }

    /**
     * Напоминаю, что множественный assert -
     * это не есть хорошо.
     * Если первый тест завалится, то второй
     * проверяться не будет.
     */
    @Test
    void whenParkedOnlyTruck() {
        Parking parking = new SurfaceParking(0, 5);
        assertThat(parking.park(new Car("Mercedes"))).isFalse();
        assertThat(parking.park(new Truck("International", 5))).isTrue();
    }

    @Test
    void whenTruckSizeIsNegative() {
        Parking parking = new SurfaceParking(1, 3);
        assertThatThrownBy(() -> parking.park(new Truck("Iveco", -3)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}