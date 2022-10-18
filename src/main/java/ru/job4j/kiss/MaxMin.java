package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

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
     * Полагаю, что здесь нужно написать итератор,
     * потому что нам нужно что-то с чем-то
     * сравнивать. А в текущем виде у нас есть
     * только текущий объект в списке.
     * Следующий объект мы взять не можем.
     *
     * Либо вложенный цикл, который начнется
     * с следующего элемента. В таком случае
     * мы сможем 2 элемента сравнить.
     *
     * Либо, нам нужно имплементировать
     * интерфейс {@link Comparable} и
     * переопределить метод compareTo.
     */
    /*public <T> T uniMethod(List<T> value) {
        Comparator<T> cmp = () -> {
            for (int i = 0; i < value.size(); i++) {
                for (int j = 0; j < value.size() - 1; j++) {
                    int resCmp = value.get(i).compareTo(value.get(j));
                }
            }
        }
    }*/
}
