package ru.job4j.ood.lsp.storegoods.control;

import ru.job4j.ood.lsp.storegoods.food.Food;
import ru.job4j.ood.lsp.storegoods.store.Store;

import java.util.Calendar;
import java.util.List;

public class ControlQuality {

    private static final Calendar CURRENT_DATE_TIME = Calendar.getInstance();

    private final Store store;

    private List<Food> incomingList;

    public ControlQuality(Store store, List<Food> incomingList) {
        this.store = store;
        this.incomingList = incomingList;
    }

    /**
     * Нужно, чтобы распределение
     * происходило в хранилищах.
     * Здесь мы не должны знать как
     * оно происходит.
     */
    public void distribute() {
        for (Food food : incomingList) {
            store.add(food);
        }
    }

    /**
     * Данный метод показывает,
     * на сколько процентов израсходован
     * срок годности.
     * Если текущая дата больше, чем
     * дата окончания срока годности, то
     * результат = 100% (товар испорчен).
     * @param food поступающий продукт.
     * @return расход срока годности в %.
     */
    private int diffShelfLife(Food food) {
        int result = 100;
        if (CURRENT_DATE_TIME.compareTo(food.getExpiryDate()) < 0) {
            long diffCreatedAndExp = diffInDays(food.getCreateDate(), food.getExpiryDate());
            long diffCreatedAndCurrent = diffInDays(food.getCreateDate(), CURRENT_DATE_TIME);
            result = (int) (diffCreatedAndCurrent / diffCreatedAndExp) * 100;
        }
        return result;
    }

    /**
     * Данный метод показывает разницу
     * в днях между датами.
     * @param first меньшая дата.
     * @param second большая дата.
     * @return разница в днях.
     */
    private long diffInDays(Calendar first, Calendar second) {
        return Math.abs(second.getTimeInMillis() - first.getTimeInMillis())
                / (1000 * 60 * 60 * 24);
    }
}
