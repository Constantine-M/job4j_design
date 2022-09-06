package ru.job4j.gc.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 1. Реализация кеша на SoftReference.
 *
 * Данный класс описывает кеширование
 * указанного файла в директории.
 *
 * @author Constantine on 28.08.2022
 */
public class DirFileCache extends AbstractCache<String, String> {

    private static final Logger LOG = LoggerFactory.getLogger(DirFileCache.class.getName());

    /**
     * Данное поле описывает кешируемую
     * папку, в которой мы будем кешировать
     * отдельно взятые файлы.
     *
     * Относительный путь работает, если
     * использовать точку.
     * "." - означает ту же папку, что и
     * текущий контекст.
     * ".." - означает родительскую папку
     * текущего контекста.
     */
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Данный метод загружает в память
     * информацию из указаного файла.
     * Далее оно будет загружено в кеш.
     *
     * Метод {@link Path#of(String, String...)}
     * чем-то похож на метод
     * {@link Path#resolve(String)} -
     * каждый раз, добавляя файл через
     * запятую, мы формируем более полный
     * путь. Пример:
     * Path.of(String bar, String foo, String buz)
     * -> bar/foo/buz
     * bar.resolve(String foo) ->
     * bar/foo...Надеюсь, что понял.
     *
     * @param key ключ получения объекта кеша.
     * @return текст, загруженный в память
     * из файла.
     */
    @Override
    protected String load(String key) {
        System.out.println("Load file to cache..");
        String content = "";
        try {
            content = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            LOG.error("Error was write to log", e);
        }
        return content;
    }
}
