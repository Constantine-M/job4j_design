package ru.job4j.ood.lsp.storegoods.control;

import ru.job4j.ood.lsp.storegoods.food.Food;
import ru.job4j.ood.lsp.storegoods.store.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс описывает
 * распределение товаров.
 */
public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * Данный метод распределяет
     * товары по хранилищам.
     * Здесь мы проходим по списку
     * хранилищ и добавляем товары.
     * Хранилища сами решают,
     * какие товары забирать себе.
     */
    public void distribute(List<Food> incomingList) {
        for (Store storage : stores) {
            for (Food food : incomingList) {
                storage.add(food);
            }
        }
    }

    /**
     * Данный метод извлекает все
     * продукты и распредляет их
     * заново.
     * 1.Проходимся по всем хранилищам.
     * 2.Извлекаем оттуда продкуты и
     * добавляем в наш список продуктов
     * для повторного распределения.
     * 3.Вновь применяем метод
     * {@link ControlQuality#distribute}.
     */
    public void resort() {
        List<Food> foodToResort = new ArrayList<>();
        for (Store storage : stores) {
            foodToResort.addAll(storage.findAll(x -> x.getPrice() > 0));
        }
        distribute(foodToResort);
    }
}
