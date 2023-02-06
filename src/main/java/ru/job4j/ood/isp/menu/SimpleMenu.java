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
     * Когда мы добавляем, то все таки нужно
     * знать и родителя, и потомка,
     * потому что от этого зависит, что
     * будет добавлено и куда.
     *
     * Есть вариации:
     * - потомок без родителя не может
     * существовать, а значит, если уже есть
     * потомок, то ничего не будет добавлено;
     * - только родитель (и он будет добавлен
     * сразу в корень);
     * - родитель и потомок, при этом сначала
     * родитель будет добавлен, а потом потомок;
     * 1.Проверяем наличие потомка. Если он есть,
     * то ничего не добавляем.
     * 2.Проверяем, что потенциально добавляемого
     * родителя нет в списке рутов. Если нет, то
     * добавляем.
     * 3.Проверяем, что родитель есть, чтобы
     * добавить потомка, которого мы передали
     * в метод.
     *
     * @param parentName корневой пункт меню.
     * @param childName подпункт меню (потомок).
     * @param actionDelegate действие с пунктом меню.
     * @return true, если пункт меню был добавлен.
     */
    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        MenuItem parentItem = new SimpleMenuItem(parentName, actionDelegate);
        MenuItem childItem = new SimpleMenuItem(childName, actionDelegate);
        Optional<ItemInfo> optItemInfo = findItem(parentName);
        if (findItem(childName).isPresent()) {
            return false;
        }
        if (Objects.equals(parentName, Menu.ROOT)) {
            rootElements.add(childItem);
        } else if (optItemInfo.isEmpty()) {
            rootElements.add(parentItem);
        } else {
            optItemInfo.get()
                    .getMenuItem()
                    .getChildren()
                    .add(childItem);
        }
        return true;
    }

    /**
     * Данный метод находит объект
     * {@link MenuItemInfo} по имени
     * пункта меню.
     * @param itemName наименование пункта меню.
     * @return {@link Optional<MenuItemInfo>}.
     */
    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> itemInfo = findItem(itemName);
        return itemInfo.map(info -> new MenuItemInfo(info.menuItem, info.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<MenuItemInfo>() {

            private final DFSIterator dfsIterator = new DFSIterator();
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.getMenuItem(), itemInfo.getNumber());
            }
        };
    }

    /**
     * Данный метод находит меню и его
     * номер по названию пункта меню.
     * Будет возвращено первое совпадение.
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
                break;
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
     * ItemInfo - это как {@link MenuItemInfo},
     * но для реализации класса {@link SimpleMenu}.
     * То есть, это такая же прослойка
     * между {@link SimpleMenu} и
     * {@link SimpleMenuItem}.
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
