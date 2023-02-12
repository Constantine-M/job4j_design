package ru.job4j.ood.isp.menu;


import java.util.function.Consumer;

public class ConsoleMenuPrinter implements MenuPrinter {

    private final Consumer<String> printer;

    /**
     * С помощью {@link Consumer} мы разорвали связь
     * с консолью для удобства тестирования.
     * Спасибо, учитель!
     * Напомню, что {@link Consumer} - это
     * функциональный интерфейс, который появился
     * в JDK 8. Его особенность: выполняет некоторое
     * действие над объектом типа T, при этом
     * ничего не возвращая.
     * То есть, если потребуется - выведем в консоль
     * или соберем то, что получилось, в строку.
     */
    public ConsoleMenuPrinter(Consumer<String> printer) {
        this.printer = printer;
    }

    @Override
    public void print(Menu menu) {
        String indention = "--";
        for (Menu.MenuItemInfo menuItem: menu) {
            int dots = dotCount(menuItem.getNumber());
            println(indention.repeat(dots)
                    .concat(menuItem.getNumber())
                    .concat(menuItem.getName()));
        }
    }

    /**
     * Данный метод подсчитывает кол-во
     * точек в номере пункта.
     * 1.Здесь я использовал метод
     * {@link String#codePoints()}.
     * Если очень грубо, то метод позволяет
     * из строки получить поток того, что
     * ты укажешь. Я указал "char" и
     * получил поток символов. Можно
     * указать int, и получишь поток
     * Integer-ов, но для этого уже есть
     * {@link java.util.stream.IntStream}
     * с его методами.
     * 2.Далее с помощью фильтра ищем
     * только точки и считаем их кол-во.
     * @param number номер пункта меню.
     * @return кол-во точек.
     */
    private int dotCount(String number) {
        return (int) number.codePoints()
                .mapToObj(c -> (char) c)
                .filter(c -> c == '.')
                .count();
    }

    private void println(String text) {
        printer.accept(text);
        printer.accept(System.lineSeparator());
    }
}
