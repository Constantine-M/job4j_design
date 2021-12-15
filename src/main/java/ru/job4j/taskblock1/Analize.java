package ru.job4j.taskblock1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 2. Статистика по коллекции.
 *
 * Данный класс анализирует начальное
 * и измененное состояние множества.
 *
 * @author Constantine on 13.12.2021
 */
public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName, (x, y) -> x, LinkedHashMap::new));
        /*while (current.iterator().hasNext()) {
            if(current.contains(map.))
        }*/
        /*for (int i = 0; i < current.size(); i++) {
            if (current.contains()) {

            }
        }*/
        return null;
    }
}
