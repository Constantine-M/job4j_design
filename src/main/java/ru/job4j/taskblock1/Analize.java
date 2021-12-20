package ru.job4j.taskblock1;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 2. Статистика по коллекции.
 *
 * Данный класс анализирует начальное
 * и измененное состояние множества.
 *
 * Чтобы обеспечить сложность O(n),
 * использовали {@link java.util.HashMap}.
 *
 * 1.Выбираем любой список и преобразуем
 * его в карту.
 * 2.Если первый список - это карта, то
 * проходиться циклом будем по второму
 * списку. Один единственный цикл в
 * программе как раз дает нам O(n).
 * Будь их 2 - сложность была бы n^2.
 * 3.В цикле проверяем - если в previous
 * (наша карта) объект с ID из текущего
 * списка {@code user.getId} находится -
 * значит эти идентификаторы есть в обоих
 * списках. Далее проверяем, что если
 * имена не совпадают, то ячейку изменяли (имя).
 * 4.Во втором условии проверяем, что если
 * в предыдущем списке объект с ID не
 * находится, то его добавили.
 * 5.Кол-во удаленных элементов
 * находим арифметически: размер списка
 * предыдущ - размер списка текущ +
 * добавленные элементы.
 *
 * @author Constantine on 13.12.2021
 */
public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        int added = 0;
        Map<Integer, String> map = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User user : current) {
            int id = user.getId();
            if (map.get(id) != null && !user.getName().equals(map.get(id))) {
                changed++;
            } else  if (map.get(id) == null) {
                added++;
            }
            deleted = map.size() - current.size() + added;
        }
        return new Info(added, changed, deleted);
    }
}
