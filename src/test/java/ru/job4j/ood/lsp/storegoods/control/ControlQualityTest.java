package ru.job4j.ood.lsp.storegoods.control;

import org.junit.Test;
import ru.job4j.ood.lsp.storegoods.datetimeparser.CalendarDateTimeParser;
import ru.job4j.ood.lsp.storegoods.datetimeparser.DateTimeParser;
import ru.job4j.ood.lsp.storegoods.food.*;
import ru.job4j.ood.lsp.storegoods.store.Shop;
import ru.job4j.ood.lsp.storegoods.store.Trash;
import ru.job4j.ood.lsp.storegoods.store.Warehouse;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ControlQualityTest {

    /**
     * В данном методе даты были
     * созданы из строки, которую мы
     * распарсили. Так тест выглядит
     * компактнее и даты удобнее
     * выставлять.
     * Прарсер был выделен в отдельный
     * интерфейс {@link DateTimeParser}.
     */
    @Test
    public void whenProductSetToTrash() {
        DateTimeParser<Calendar> parser = new CalendarDateTimeParser();
        Calendar created = parser.parse("01-01-1970 00:00");
        Calendar expired = parser.parse("03-12-2022 00:00");
        Food eggs = new Eggs("Eggs", expired, created, 10000, 0);
        List<Food> list = List.of(eggs);
        ExpirationCalculator<Calendar> calculator = new CalendarExpirationCalculator();
        Trash trash = new Trash(calculator);
        ControlQuality control = new ControlQuality(List.of(trash));
        control.distribute(list);
        assertThat(trash.findAll(food -> food.getName().equals("Eggs"))).contains(eggs);
    }

    @Test
    public void whenFirstProductSetToWarehouse() {
        DateTimeParser<Calendar> parser = new CalendarDateTimeParser();
        Calendar created1 = parser.parse("01-12-2022 07:00");
        Calendar expired1 = parser.parse("31-12-2022 23:00");
        Calendar created2 = parser.parse("01-01-1970 00:00");
        Calendar expired2 = parser.parse("03-12-2022 00:00");
        Food beef = new FrozenBeef("Beef", expired1, created1, 4521, 0);
        Food milk = new Milk("Sour milk", expired2, created2, 2000, 0);
        List<Food> list = List.of(beef, milk);
        ExpirationCalculator<Calendar> calculator = new CalendarExpirationCalculator();
        Trash trash = new Trash(calculator);
        Warehouse warehouse = new Warehouse(calculator);
        ControlQuality control = new ControlQuality(List.of(trash, warehouse));
        control.distribute(list);
        assertThat(warehouse.findAll(f -> f.getName().equals("Beef"))).contains(beef);
    }

    @Test
    public void whenProductExpirationIsLessThan75AndSetToShop() {
        DateTimeParser<Calendar> parser = new CalendarDateTimeParser();
        Calendar beefCreated = parser.parse("01-12-2022 07:00");
        Calendar beefExpired = parser.parse("31-12-2022 23:00");
        Calendar pastaCreated = parser.parse("20-11-2022 00:00");
        Calendar pastaExpired = parser.parse("20-12-2022 00:00");
        Food beef = new FrozenBeef("Beef", beefExpired, beefCreated, 4521, 0);
        Food pasta = new Pasta("Mama Mia", pastaExpired, pastaCreated, 100, 0);
        List<Food> list = List.of(beef, pasta);
        ExpirationCalculator<Calendar> calculator = new CalendarExpirationCalculator();
        Shop shop = new Shop(calculator);
        Warehouse warehouse = new Warehouse(calculator);
        ControlQuality control = new ControlQuality(List.of(shop, warehouse));
        control.distribute(list);
        assertThat(shop.findAll(f -> f.getName().equals("Mama Mia"))).contains(pasta);
    }

    @Test
    public void whenProductExpirationIsGreaterThan75AndSetToShop() {
        DateTimeParser<Calendar> parser = new CalendarDateTimeParser();
        Calendar milkCreated = parser.parse("01-12-2022 07:00");
        Calendar milkExpired = parser.parse("06-12-2022 23:00");
        Calendar pastaCreated = parser.parse("20-11-2022 00:00");
        Calendar pastaExpired = parser.parse("20-01-2023 00:00");
        Food milk = new Milk("Nemoloko", milkExpired, milkCreated, 100, 0);
        Food pasta = new Pasta("Mama Mia", pastaExpired, pastaCreated, 3442, 0);
        List<Food> list = List.of(milk, pasta);
        ExpirationCalculator<Calendar> calculator = new CalendarExpirationCalculator();
        Shop shop = new Shop(calculator);
        Warehouse warehouse = new Warehouse(calculator);
        ControlQuality control = new ControlQuality(List.of(shop, warehouse));
        control.distribute(list);
        assertThat(shop.findAll(f -> f.getName().equals("Nemoloko"))
                .stream()
                .findFirst()
                .get()
                .getPrice()).isEqualTo(70);
    }
}