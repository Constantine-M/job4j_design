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
     * в том, что мы создаем
     * безопасную ссылку на кешируемую
     * информацию в виде объекта V.
     * Если GC затрет сильную ссылку,
     * то мы все равно сможем получить
     * информацию.
     *
     * @param key ключ получения объекта кеша.
     * @param value значение.
     */
    public void put(K key, V value) {
        SoftReference<V> softRefVal = new SoftReference<>(value);
        cache.put(key, softRefVal);
    }

    /**
     * Данный метод получает информацию
     * из кеша.
     * 1.Проверяем наличие ключа в кеше.
     * Этот пункт мы сделали, когда
     * использовали метод
     * {@link Map#getOrDefault}.
     * По дефолту возвращаем безопасную
     * ссылку на null.
     * 2.Далее проверяем, что кеш (данные,
     * которые мы получили по безопасной
     * ссылке) не null, потому что GC мог
     * их стереть.
     * 3.Если кеш = null, то загружаем
     * данные по ключу.
     *
     * Здесь мы работаем только с
     * сильной ссылкой. Безопасная
     * ссылка заложена в методе
     * {@link AbstractCache#put}.
     *
     * @param key ключ получения объекта кеша.
     * @return данные, которые мы получаем
     * по безопасной ссылке.
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    /**
     * Данный метод является "фабричным".
     * Мы выносим весь код загрузки
     * информации в кеш, в отдельный
     * метод, т.к. кешировать можно
     * все что угодно.
     *
     * @param key ключ получения объекта кеша.
     * @return безопасная ссылка на
     * кешируемый объект.
     */
    protected abstract V load(K key);
}
