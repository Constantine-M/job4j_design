package ru.job4j.gc.profiling;

/**
 * @author Constantine on 01.08.2022
 */
public class ExitAction implements Action {

    private final Output out;

    public ExitAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit program";
    }

    @Override
    public boolean execute(Input input, Data data) {
        out.println("EXITING PROGRAM...BYE");
        return false;
    }
}
