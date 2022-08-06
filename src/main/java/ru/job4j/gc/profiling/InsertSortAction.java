package ru.job4j.gc.profiling;

/**
 * Данный класс описывает действие,
 * которое выполняет сортировку
 * вставкой.
 *
 * @author Constantine on 03.08.2022
 */
public class InsertSortAction implements Action {

    private final Output out;

    public InsertSortAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "InsertSort";
    }

    @Override
    public boolean execute(Input input, Data data) {
        if (data != null) {
            new InsertSort().sort(data);
        } else {
            out.println("There is no data. Nothing to sort.");
        }
        return true;
    }
}
