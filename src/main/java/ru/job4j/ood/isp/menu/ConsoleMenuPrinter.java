package ru.job4j.ood.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        String indention = "--";
        while (menu.iterator().hasNext()) {
            Menu.MenuItemInfo menuItemInfo = menu.iterator().next();
            System.out.println(indention.concat(
                    menuItemInfo.getNumber()).concat(
                            menuItemInfo.getName()));
        }
    }

  /*  private String addIndention(Menu menu) {
        String indention = "--";
        while (menu.iterator().hasNext()) {
            if (menu.iterator().next().getNumber)
        }
        return null;
    }

    private String addIndentionVer1(Menu menu) {
        String indention = "--";
        while (menu.iterator().hasNext()) {
            if (menu.iterator().next().getChildren()) {

            }
        }
        if (!menuItemInfo.getChildren().isEmpty()) {

        }
        return null;
    }*/
}
