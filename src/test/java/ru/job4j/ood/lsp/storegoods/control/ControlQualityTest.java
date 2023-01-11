package ru.job4j.ood.lsp.storegoods.control;

import org.junit.jupiter.api.Test;
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
     * Во второй итерации правки тестов
     * парсер уже стал не нужен, но
     * я его оставлю, т.к. проект
     * учебный. Тут все кругом изучается
     * и тестируется. Вдруг он пригодится.
     * А чтобы тесты были актуальны всегда,
     * мы воспользуемся методами
     * {@link Calendar#add} или
     * {@link Calendar#roll}. Они добавляют
     * к текущей дате или убавляют.
     */
    @Test
    public void whenProductSetToTrash() {
        Calendar created = Calendar.getInstance();
        Calendar expired = Calendar.getInstance();
        expired.add(Calendar.DAY_OF_MONTH, -1);
        created.add(Calendar.DAY_OF_MONTH, 1);
        Food eggs = new Eggs("Eggs", expired, created, 10000, 0);
        List<Food> list = List.of(eggs);
        ExpirationCalculator<Calendar> calculator = new CalendarExpirationCalculator();
        Trash trash = new Trash(calculator);
        ControlQuality control = new ControlQuality(List.of(trash));
        control.distribute(list);
        assertThat(trash.findAll(food -> food.getName().equals("Eggs"))).contains(eggs);
    }

    @Test
    public void whenBeefProductSetToWarehouse() {
        var beefCreated = Calendar.getInstance();
        var beefExpired = Calendar.getInstance();
        var milkCreated = Calendar.getInstance();
        var milkExpired = Calendar.getInstance();
       beefExpired.roll(Calendar.MONTH, true);
       milkExpired.roll(Calendar.DAY_OF_MONTH, true);
        Food beef = new FrozenBeef("Beef", beefExpired, beefCreated, 4521, 0);
        Food milk = new Milk("Sour milk", milkExpired, milkCreated, 2000, 0);
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
        Calendar beefCreated = Calendar.getInstance();
        Calendar beefExpired = Calendar.getInstance();
        Calendar pastaCreated = Calendar.getInstance();
        Calendar pastaExpired = Calendar.getInstance();
        pastaCreated.add(Calendar.DAY_OF_MONTH, -2);
        pastaExpired.add(Calendar.DAY_OF_MONTH, 4);
        beefExpired.roll(Calendar.MONTH, true);
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
    public void whenProductExpirationIsGreaterThan75AndSetToShopWithDiscount() {
        Calendar milkCreated = Calendar.getInstance();
        Calendar milkExpired = Calendar.getInstance();
        Calendar pastaCreated = Calendar.getInstance();
        Calendar pastaExpired = Calendar.getInstance();
        pastaExpired.roll(Calendar.MONTH, true);
        milkCreated.add(Calendar.DAY_OF_MONTH, -7);
        milkExpired.add(Calendar.DAY_OF_MONTH, 2);
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

    /**
     * Метод {@link Calendar#roll(int, boolean)}
     * используется, если нам необходимо
     * просто увеличить или уменьшить
     * дату на единицу. Например, увеличить
     * на месяц, на год, на день.
     * НЕ НА КОНКРЕТНОЕ ЧИСЛО ДНЕЙ, МЕСЯЦЕВ, ЛЕТ,
     * А НА ЕДИНИЦУ ВРЕМЕНИ (один день,
     * один месяц, один год...).
     * Чтобы увеличить, нужно передать в метод
     * true, а чтобы уменьшить - false.
     * Метод {@link Calendar#add(int, int)}
     * используется, если нам необходимо
     * увеличить/уменьшить дату на определенное
     * кол-во часов, дней, месяцев, лет и т.д.
     * Чтобы увеличить, в метод передаем
     * положительное число, а чтобы уменьшить -
     * отрицательное.
     */
    @Test
    public void whenMilkExpirationIs100AndNotSetToShop() {
        Calendar milkCreated = Calendar.getInstance();
        Calendar milkExpired = Calendar.getInstance();
        Calendar pastaCreated = Calendar.getInstance();
        Calendar pastaExpired = Calendar.getInstance();
        pastaExpired.add(Calendar.MONTH, 1);
        milkCreated.add(Calendar.DAY_OF_MONTH, -7);
        milkExpired.add(Calendar.DAY_OF_MONTH, -1);
        Food milk = new Milk("Nemoloko", milkExpired, milkCreated, 100, 0);
        Food pasta = new Pasta("Mama Mia", pastaExpired, pastaCreated, 3442, 0);
        List<Food> list = List.of(milk, pasta);
        ExpirationCalculator<Calendar> calculator = new CalendarExpirationCalculator();
        Shop shop = new Shop(calculator);
        ControlQuality control = new ControlQuality(List.of(shop));
        control.distribute(list);
        assertThat(shop.findAll(f -> f.getName().equals("Nemoloko"))).isNotIn(shop);
    }

    /**
     * Множественный assertThat - это
     * не есть хорошо. Если первый assertThat
     * завалится, то остальные не будут
     * проверены, а значит смысла в них
     * большого нет. Но я специально
     * указал все три, чтобы написать
     * про этот нюанс, а заодно все
     * таки проверить все хранилища.
     */
    @Test
    public void whenControlQualityDistributeAllProducts() {
        Calendar milkCreated = Calendar.getInstance();
        Calendar milkExpired = Calendar.getInstance();
        milkCreated.add(Calendar.DAY_OF_MONTH, -10);
        milkExpired.roll(Calendar.DAY_OF_MONTH, false);
        Calendar pastaCreated = Calendar.getInstance();
        Calendar pastaExpired = Calendar.getInstance();
        pastaCreated.add(Calendar.DAY_OF_MONTH, -10);
        pastaExpired.add(Calendar.DAY_OF_MONTH, 10);
        Calendar beefCreated = Calendar.getInstance();
        Calendar beefExpired = Calendar.getInstance();
        beefExpired.add(Calendar.MONTH, 1);
        Food milk = new Milk("Nemoloko", milkExpired, milkCreated, 130, 0);
        Food pasta = new Pasta("Barilla", pastaExpired, pastaCreated, 532, 0);
        Food beef = new FrozenBeef("Beef", beefExpired, beefCreated, 11111, 0);
        List<Food> goods = List.of(milk, pasta, beef);
        ExpirationCalculator<Calendar> calculator = new CalendarExpirationCalculator();
        Trash trash = new Trash(calculator);
        Shop shop = new Shop(calculator);
        Warehouse warehouse = new Warehouse(calculator);
        ControlQuality control = new ControlQuality(List.of(trash, shop, warehouse));
        control.distribute(goods);
        assertThat(shop.findAll(f -> true).contains(pasta)).isTrue();
        assertThat(trash.findAll(f -> true).contains(milk)).isTrue();
        assertThat(warehouse.findAll(f -> true).contains(beef)).isTrue();
    }
}