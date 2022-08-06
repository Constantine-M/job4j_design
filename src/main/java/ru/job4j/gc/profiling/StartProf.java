package ru.job4j.gc.profiling;

import java.util.List;
import java.util.Random;

/**
 * 1. Эксперименты с различными GC.
 * В этом задании мы будем профилировать
 * различные типы сортировки.
 *
 * @author Constantine on 31.07.2022
 */
public class StartProf {

    private Output output;

    public StartProf(Output output) {
        this.output = output;
    }

    /**
     * Данный метод инициализирует работу
     * приложения.
     * Так как мы вводим информацию
     * в консоли, то на входе {@link Input}.
     * Мы будем работать с данными, любыми.
     * Поэтому использовали интерфейс {@link Data}.
     * Все действия были собраны в
     * одном интерфейсе {@link Action}.
     * @param input - интерфейс для ввода информации.
     * @param data - данные, которые будем
     *             сортировать.
     * @param actions - все возможные действия
     *                нашего приложения.
     */
    public void init(Input input, Data data, List<Action> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 1 || select > actions.size()) {
                output.println("Wrong input, you can select: 1 .. " + (actions.size()));
                continue;
            }
            Action action = actions.get(select - 1);
            run = action.execute(input, data);
        }
    }

    private void showMenu(List<Action> actions) {
        output.println("Menushka.");
        for (int i = 0; i < actions.size(); i++) {
            output.println((i + 1) + "." + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input input = new ConsoleInput();
        Data data = new RandomArray(new Random());
        List<Action> actions = List.of(
                new CreateArray(out),
                new BubbleSortAction(out),
                new MergeSortAction(out),
                new InsertSortAction(out),
                new ExitAction(out)
        );
        new StartProf(out).init(input, data, actions);
    }
}
