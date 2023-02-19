package ru.job4j.ood.isp.menu;

import java.util.List;

/**
 * Данный интерфейс описывает элемент меню.
 * В ТЗ указано, что пункт меню имеет имя и
 * дочерние элементы.
 * Также в последнем пункте было указано, что
 * нужно "предусмотреть возможность определять
 * действие, когда пользователь выбирает
 * конкретный пункт меню", а это значит, что
 * пункт меню и действие связаны.
 * "Но связаны НЕ через стороннюю структуру
 * (напр. {@link java.util.Map}), а через сам
 * элемент меню, потому что на элементах меню
 * строится само меню.
 */
public interface MenuItem {

    String getName();

    List<MenuItem> getChildren();

    ActionDelegate getActionDelegate();
}