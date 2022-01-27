package ru.job4j.io.duplicates;

import java.util.Objects;

/**
 * 4.2. Поиск дубликатов.
 *
 * Данный класс описывает файл,
 * который имеет какое-то имя и размер.
 *
 * @author Constantine on 25.01.2022
 */
public class FileProperty {

    private long size;

    private String name;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    @Override
    public String toString() {
        return "FileProperty{"
                + "size = " + size
                + ", name = '" + name + '\''
                + '}';
    }
}
