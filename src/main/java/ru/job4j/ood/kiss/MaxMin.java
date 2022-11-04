package ru.job4j.ood.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Данный класс описывает поиск
 * максимального и минимального элемента
 * по критерию {@link Comparator}.
 */
public class MaxMin {

    /**
     * Если этот метод возвращает отрицательное число,
     * то текущий объект будет располагаться перед тем,
     * который передается через параметр. Если метод
     * вернет положительное число, то, наоборот, после
     * второго объекта. Если метод возвратит ноль,
     * значит, оба объекта равны.
     *
     * @param value список сравниваемых объектов.
     * @param comparator компаратор - штатный или самописный.
     * @return максимальное число/объект.
     * @param <T> объект
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, (a, b) -> comparator.compare(a, b) < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, (a, b) -> comparator.compare(a, b) > 0);
    }

    /**
     * Данный метод производит сравнение.
     * 1.Мы проходмся по списку.
     * 2.В {@link BiPredicate} поступает 2
     * сравниваемых объекта.
     * 3.Если условие предиката
     * выполняется, то начальному значению
     * присваивается новое значение.
     *
     * Например, если нужно макс число..
     * Если текущее число меньше чем
     * того, с которым сравниваем, то
     * текущему числу присваиваем значение
     * числа, с которым сравнивали
     * (то есть макс число).
     */
    public <T> T compare(List<T> list, BiPredicate<T, T> pred) {
        validate(list);
        T result = list.get(0);
        for (T value : list) {
            if (pred.test(result, value)) {
                result = value;
            }
        }
        return result;
    }

    private <T> void validate(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }
    }
}
