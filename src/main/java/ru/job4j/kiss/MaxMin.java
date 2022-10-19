package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Данный класс описывает поиск
 * максимального и минимального элемента
 * по критерию {@link Comparator}.
 */
public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return null;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return null;
    }

    /**
     * Данный метод производит сравнение.
     * В {@link BiPredicate} поступает 2
     * аргумента - это наш объект и
     * компаратор, кторый будет сравнивать.
     * Получается, нам нужно все таки написать
     * свой компаратор (просто чтоб был), а в
     * методах min/max менять на тот, что передаем
     * в метод.
     * {@link BiPredicate} без аргументов не
     * работает. Значит, нам нужно добавить во
     * входные параметры компаратор, чтобы
     * потом его можно было поменять на другой.
     */
    /*public <T> T uniMethod(List<T> list, BiPredicate<T, Comparator<T>> pred) {
        validate(list);
        T result = list.get(0);
        for (T value : list) {
            if (pred.test(value, missing argument - have to use some Comparator)) {
                result = value;
            }
        }
        return result;
    }*/

    private <T> void validate(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }
    }
}
