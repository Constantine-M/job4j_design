package ru.job4j.gc.profiling;

import java.util.Random;

/**
 * @author Constantine on 31.07.2022
 */
public class CreateArray implements Action {

    private final Output out;

    public CreateArray(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create an array";
    }

    @Override
    public boolean execute(Input input, Data data) {
        out.println("CREATING AN ARRAY...");
        Random random = new Random();
        data.insert(250000);
        return true;
    }
}
