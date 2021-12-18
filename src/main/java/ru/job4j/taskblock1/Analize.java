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
        int deleted = 0;
        int changed = 0;
        int added = 0;
        Map<Integer, String> map = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName, (x, y) -> x, LinkedHashMap::new));
        for (User user : current) {
            if (map.get(user.getId()) != null && !user.getName().equals(map.get(user.getId()))) {
                changed++;
            } else  if (map.get(user.getId()) == null) {
                added++;
            }
        }
        /*for (Map.Entry entry : map.entrySet()) {
            if (current.contains(entry.getKey())) {

            }
        }*/
        /*if (map.size() > current.size()) {
            deleted = map.size() - current.size();
        } else {
            added = current.size() - map.size();
        }*/
        /*Map<Integer, User> map = new LinkedHashMap<>();
        int i = 0;
        for (User users : previous) {
            map.put(i, users);
        }
        for (User user : previous) {
            for (int j = 0; j < current.size(); j++) {
                if (!current.contains(user)) {
                    deleted++;
                }
            }
        }*/
        /*while (current.iterator().hasNext()) {
            if(current.contains(map.))
        }*/
        /*for (int i = 0; i < current.size(); i++) {
            if (current.contains()) {

            }
        }*/
        return new Info(added, changed, deleted);
    }
}
