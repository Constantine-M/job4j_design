package ru.job4j.gc.profiling;

/**
 * Данный класс описывает действие,
 * которое выполняет пузырьковую
 * сортировку.
 *
 * @author Constantine on 01.08.2022
 */
public class BubbleSortAction implements Action {

    private final Output out;

    public BubbleSortAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Bubble Sort";
    }

    @Override
    public boolean execute(Input input, Data data) {
        if (data != null) {
            new BubbleSort().sort(data);
        } else {
            out.println("There is no data. Nothing to sort.");
        }
        return true;
    }
}
