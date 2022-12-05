package ru.job4j.ood.lsp.storegoods.store;

import ru.job4j.ood.lsp.storegoods.food.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Данный класс является некоторой
 * прослойкой между {@link Store} и
 * конкретными реализациями этого
 * интерфейса.
 * Абстрактный класс частично
 * реализует логику хранилищ, чтобы
 * она не дублировалась в других
 * классах.
 */
public abstract class AbstractStore implements Store {

    private final List<Food> listFood = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (isNotExpired(food)) {
            listFood.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Food food) {
        validate();
        boolean result = false;
        if (listFood.contains(food)) {
            listFood.remove(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> findAll(Predicate<Food> filter) {
        validate();
        return listFood.stream().filter(filter).toList();
    }

    /**
     * Данный метод делает проверку
     * списка. Считаю ее необходимой,
     * но пока дальше проверки на
     * пустоту не зашел.
     */
    private void validate() {
        if (listFood.isEmpty()) {
            throw new IllegalArgumentException("List of foods is empty!");
        }
    }

    /**
     * Данный метод делает проверку
     * срока годности.
     *
     * У каждой реализации {@link AbstractStore}
     * будет своя реализация данного метода.
     * @param food передаваемый в метод продукт.
     * @return true, если срок годности не истек,
     *          иначе false.
     */
    protected abstract boolean isNotExpired(Food food);
}
