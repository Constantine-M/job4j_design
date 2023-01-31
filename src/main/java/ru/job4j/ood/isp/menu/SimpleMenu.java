package ru.job4j.ood.isp.menu;

import javax.swing.text.html.Option;
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
        if (findItem(childName).isPresent()) {
            return false;
        }
        if (Objects.equals(parentName, Menu.ROOT)) {
            rootElements.add(childItem);
        } else if (!hasElementInRootList(parentName)) {
            rootElements.add(parentItem);
        }
        if (findItem(parentName).isPresent()) {
            rootElements.add(childItem);
            getMenuItemByName(parentName).get().getChildren().add(childItem);
        }
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> itemInfo = findItem(itemName);
        if (itemInfo.isPresent()) {
            return Optional.of(new MenuItemInfo(itemInfo.get().menuItem, itemInfo.get().number));
        }
        return Optional.empty();
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return null;
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
     * Данный метод проходится по списку всех
     * пунктов меню и проверяет, есть ли
     * там пункт с названием, который мы передали
     * в метод.
     * Так я пытаюсь избежать использования
     * equals & hashcode, которого на мой
     * взгляд здесь быть не должно.
     * @param element название пункта меню.
     * @return true, если такой пункт уже есть.
     */
    private boolean hasElementInRootList(String element) {
        for (MenuItem item : rootElements) {
            if (item.getName().equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Данный метод находит объект
     * пункта меню {@link MenuItem}
     * по имени пункта.
     * Через {@link Optional} быстрее и
     * безопаснее, но вторым я в
     * {@link SimpleMenu#add} пренебрег,
     * потому что уверен, что нужный
     * элемент в списке есть.
     * @param name имя пункта меню.
     * @return пункт меню.
     */
    private Optional<MenuItem> getMenuItemByName(String name) {
        return rootElements.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();
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


        /**
         * В каркасе не было equals & hashcode.
         * Я специально добавил их, чтобы
         * можно было проверять наличие
         * parent в списке rootElements,
         * в методе {@link SimpleMenu#add}.
         * Это было так:
         * rootElements.contains(parentItem).
         * Считаю это костылем, потому что
         * equals & hashcode определен в
         * {@link Menu}.
         * Чуть позже я написал по-другому,
         * пришлось написать метод
         * {@link SimpleMenu#hasElementInRootList}.
         */
        @Override
        public boolean equals(Object o) {
            boolean result;
            if (this == o) {
                result = true;
            } else if (!(o instanceof SimpleMenuItem that)) {
                result = false;
            } else {
                result = Objects.equals(name, that.name)
                        && Objects.equals(children, that.children)
                        && Objects.equals(actionDelegate, that.actionDelegate);
            }
            return result;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, actionDelegate);
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
