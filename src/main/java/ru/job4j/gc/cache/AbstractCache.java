package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Реализация кеша на SoftReference.
 *
 * @author Constantine on 28.08.2022
 */
public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Данный метод добавляет в {@link HashMap}
     * наши объекты K key и V value.
     * Это может быть все что угодно.
     *
     * Особенность данного метода заключается
     * в проверке, имеется ли что-то
     * (файл например, который хотим кешировать)
     * в кеше.
     *
     * Если нет, то
     * 1.Загружаем информацию и
     * оборачиваем ее в виде объекта.
     * 2.Создаем безопасную ссылку
     * {@link SoftReference} на
     * кешируемый объект, чтобы
     * в дальнейшем работать с
     * ней, а не с сильной ссылкой.
     *
     * @param key ключ получения объекта кеша.
     * @param value значение.
     */
    public void put(K key, V value) {
        if (get(key) == null) {
            value = load(key);
            SoftReference<V> softRefVal = new SoftReference<>(value);
            cache.put(key, softRefVal);
        }
    }

    /**
     * Данный метод возвращает безопасную
     * ссылку на то, что мы кешируем.
     *
     * @param key ключ получения объекта кеша.
     * @return безопасная ссылка на
     * объект кеша.
     */
    public V get(K key) {
        return cache.get(key).get();
    }

    /**
     * Данный метод является "фабричным".
     * Мы выносим весь код загрузки
     * инфомрации в кеш,в отдельный
     * метод, т.к. кешировать можно
     * все что угодно.
     *
     * @param key ключ получения объекта кеша.
     * @return безопасная ссылка на
     * кешируемый объект.
     */
    protected abstract V load(K key);
}
