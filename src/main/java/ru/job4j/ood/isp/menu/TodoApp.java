package ru.job4j.ood.isp.menu;

import java.util.Optional;

public class TodoApp {

    private static final int ADD_TO_ROOT_ACTION = 1;

    private static final int ADD_TO_PARENT_ACTION = 2;

    private static final int CALL_ASSIGNED_ACTION = 3;

    private static final int PRINT_ACTION = 4;


    public static final String SELECT = "Select menu: ";

    public static final String CHILD_ITEM = "Enter the menu item name: ";

    public static final String PARENT_ITEM = "Enter the parent menu item name: ";

    public static final String EXIT = "Shutting down...";

    public static final String MENU = """
                1. Add menu item to the root.
                2. Add menu item to parent (menu sub-item).
                3. Perform an action.
                4. Display menu in console.
                5. Exit.
            """;

    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action...");

    private static String userChoiceChildName;

    private static String userChoiceParentName;

    private static void run(Menu menu, MenuPrinter printer, Input input) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(input.askStr(SELECT));
            switch (userChoice) {
                case ADD_TO_ROOT_ACTION -> {
                    userChoiceChildName = input.askStr(CHILD_ITEM);
                    menu.add(Menu.ROOT, userChoiceChildName, DEFAULT_ACTION);
                }
                case ADD_TO_PARENT_ACTION -> {
                    userChoiceChildName = input.askStr(CHILD_ITEM);
                    userChoiceParentName = input.askStr(PARENT_ITEM);
                    menu.add(userChoiceParentName, userChoiceChildName, DEFAULT_ACTION);
                }
                case CALL_ASSIGNED_ACTION -> {
                    userChoiceChildName = input.askStr(CHILD_ITEM);
                    Optional<Menu.MenuItemInfo> itemInfo = menu.select(userChoiceChildName);
                    if (itemInfo.isEmpty()) {
                        System.out.println("Menu item not found!");
                    } else {
                        itemInfo.get()
                                .getActionDelegate()
                                .delegate();
                    }
                }
                case PRINT_ACTION -> {
                    printer.print(menu);
                }
                default -> {
                    run = false;
                    System.out.println(EXIT);
                }
            }
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsoleMenuPrinter(System.out::println);
        run(menu, printer, input);
    }
}
