package ru.job4j.generics.containers;

import java.util.HashMap;
import java.util.Map;

/**
 * 5.2.2. Реализовать Store<T extends Base>.
 *
 * MemStore - это универсальное хранилище.
 * 1.Наш класс принимает параметр <T>,
 * который является объектом класса
 * {@link Base} или объектом класса,
 * который наследуется от {@link Base}.
 * 2.Реализуется от {@link Store}, который
 * принимает параметр <T>.
 * @author Constantine on 27.10.2021
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    /**
     * Данный метод добавляет объект в
     * хранилище (карту).
     * @param model объект класса {@link Base}
     *              или его наследник.
     */
    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    /**
     * Данный метод изменяет значение
     * (объект) по номеру id.
     * По id ищем пару и в ней меняем одну
     * модель на другую?
     * @param id номер объекта.
     * @param model объект (предмет).
     */
    @Override
    public void replace(String id, T model) {

        for (String key : mem.keySet()) {
            if (key.equals(id)) {
                mem.put(key, model);
            }
        }
   }

    @Override
    public void delete(String id) {
        mem.remove(id);
    }

    /**
     * Данный метод находит значение (объект)
     * по ключу (id).
     * @param id номер объекта.
     * @return объект в хранилище.
     */
    @Override
    public T findById(String id) {
        T model = null;
        for (String key : mem.keySet()) {
            if (key.equals(id)) {
                model = mem.get(key);
            }
        }
        return model;
    }
}
