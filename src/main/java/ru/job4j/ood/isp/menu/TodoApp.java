package ru.job4j.ood.isp.menu;

public class TodoApp {

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

    private static final ActionDelegate DEFAULT_ACTION = System.out::println;

    private static String userChoiceChildName;

    private static String userChoiceParentName;

    private static void run(Menu menu, MenuPrinter printer, Input input) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(input.askStr(SELECT));
            switch (userChoice) {
                case 1 -> {
                    userChoiceChildName = input.askStr(CHILD_ITEM);
                    menu.add(Menu.ROOT, userChoiceChildName, DEFAULT_ACTION);
                }
                case 2 -> {
                    userChoiceChildName = input.askStr(CHILD_ITEM);
                    userChoiceParentName = input.askStr(PARENT_ITEM);
                    menu.add(userChoiceParentName, userChoiceChildName, DEFAULT_ACTION);
                }
                case 3 -> {
                    userChoiceChildName = input.askStr(CHILD_ITEM);
                    menu.select(userChoiceChildName).get()
                            .getActionDelegate()
                            .delegate();
                }
                case 4 -> {
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
