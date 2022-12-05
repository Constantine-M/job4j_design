package ru.job4j.ood.lsp.storegoods.control;

import ru.job4j.ood.lsp.storegoods.food.Food;
import ru.job4j.ood.lsp.storegoods.store.Store;

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
}
