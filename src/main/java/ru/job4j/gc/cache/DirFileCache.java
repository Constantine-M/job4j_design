package ru.job4j.gc.cache;

/**
 * 1. Реализация кеша на SoftReference.
 *
 * Данный класс описывает кеширование
 * указанного файла в директории.
 *
 * @author Constantine on 28.08.2022
 */
public class DirFileCache extends AbstractCache<String, String> {

    /**
     * Относительный путь работает, если
     * использовать точку.
     * . означает ту же папку, что и текущий контекст.
     * .. означает родительскую папку текущего контекста.
     */
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {

        return null;
    }
}
