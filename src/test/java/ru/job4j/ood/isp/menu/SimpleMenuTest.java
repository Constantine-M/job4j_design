package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {

    /**
     * Данное действие над пунктом меню
     * является своеобразной заглушкой.
     */
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectedMenuAboutSOLID() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Go to school", STUB_ACTION);
        menu.add("Go to school", "Argue with the Teacher", STUB_ACTION);
        menu.add("Go to school", "Smoking behind the school building", STUB_ACTION);
        menu.add(Menu.ROOT, "Attend a basketball training session", STUB_ACTION);
        menu.add(Menu.ROOT, "Learn JAVA", STUB_ACTION);
        menu.add("Learn JAVA", "Study SOLID block", STUB_ACTION);
        menu.add("Study SOLID block", "Study ISP", STUB_ACTION);
        menu.add("Study SOLID block", "Study DIP", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo(
                "Study SOLID block",
                List.of("Study ISP", "Study DIP"),
                STUB_ACTION, "3.1."))
                .isEqualTo(menu.select("Study SOLID block").get());
    }

    /**
     * Сам по себе Consumer ничего не
     * возвращает, но мы туда можем
     * что-нибудь подложить и использовать это.
     * Например, заранее создали {@link StringBuilder},
     * поместили его в {@link Consumer},
     * заполнили {@link StringBuilder}
     * и сравнили с тем, который прописали сами
     * (тот, который ожидали).
     */
    @Test
    public void whenPrintMenuWithIndention() {
        StringBuilder actualBuilder = new StringBuilder();
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsoleMenuPrinter(actualBuilder::append);
        menu.add(Menu.ROOT, "Go to work", STUB_ACTION);
        menu.add("Go to work", "Coffee break", STUB_ACTION);
        menu.add("Go to work", "Work hard!", STUB_ACTION);
        menu.add("Work hard!", "Argue with the initiator", STUB_ACTION);
        menu.add("Work hard!", "Offer my own solution", STUB_ACTION);
        menu.add(Menu.ROOT, "Go home and learn Java", STUB_ACTION);
        StringBuilder expectedBuilder = new StringBuilder();
        String ls = System.lineSeparator();
        expectedBuilder.append("--1.Go to work").append(ls)
                .append("----1.1.Coffee break").append(ls)
                .append("----1.2.Work hard!").append(ls)
                .append("------1.2.1.Argue with the initiator").append(ls)
                .append("------1.2.2.Offer my own solution").append(ls)
                .append("--2.Go home and learn Java").append(ls);
        printer.print(menu);
        assertThat(actualBuilder.toString()).isEqualTo(expectedBuilder.toString());
    }
}