package ru.job4j.ood.isp.menu;

import java.util.*;

/**
 * Данный класс описывает меню.
 */
public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    /**
     * Данный метод добавляет в меню
     * новый пункт.
     *
     * Пока что непонятно, зачем здесь
     * родитель и ребенок. Нужно ведь добавить
     * одного. У пункта меню только одно имя...
     * Но ведь нужно еще сделать подменю...
     *
     * @param parentName
     * @param childName
     * @param actionDelegate
     * @return
     */
    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return Optional.empty();
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return null;
    }

    /**
     * Данный метод находит меню и его
     * номер по названию пункта меню.
     *
     * Объявление итератора - это наверное нехорошо...
     * @param name имя пункта, который ищем.
     * @return пункт и его номер.
     */
    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rsl = Optional.empty();
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo el = dfsIterator.next();
            if (el.getMenuItem().getName().equals(name)) {
                rsl = Optional.of(el);
            }
        }
        return rsl;
    }

    /**
     * Данный класс описывает реализацию
     * {@link MenuItem} для {@link SimpleMenu}.
     */
    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    /**
     * Данный класс описывает итератор, основанный
     * на поиске в глубину. Но немного модифицированный.
     * Для реализации итератора мы использовали
     * двунаправленные очереди - {@link Deque}.
     * Они могут работать как стек.
     * Первая очередь - это пункты меню (имена).
     * Вторая очередь - это номера этих пунктов.
     */
    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        /**
         * В данном конструкторе мы проходимся
         * по списку элементов (рут элементов -
         * элемнтов, из которых и будет формироваться
         * наше меню) и разбираем его,
         * заполняя списки stack и numbers.
         * Stack - это сам пункт меню, а
         * numbers - это его номер (тот самый
         * пример с 1.1.1).
         */
        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Данный метод возвращает следующий пункт меню.
         * Здесь реализован обход дерева в глубину,
         * но при этом обход происходит сверху вниз
         * и слева направо.
         * @return пункт меню и его номер.
         */
        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    /**
     * Данный класс служит для того, чтобы
     * скомпоновать пункт меню и номер
     * пункта (1.1., 1.1.1. и т.д.).
     * Здесь я добавил геттеры, чтобы
     * использовать их в {@link SimpleMenu#findItem}.
     */
    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public String getNumber() {
            return number;
        }
    }
}
