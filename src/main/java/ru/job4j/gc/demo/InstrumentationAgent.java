package ru.job4j.gc.demo;

import java.lang.instrument.Instrumentation;

/**
 * @author Constantine on 25.07.2022
 */
public class InstrumentationAgent {

    private static volatile Instrumentation globalInstrumentation;

    public static void premain(final String agentArgs, final Instrumentation inst) {
        globalInstrumentation = inst;
    }

    public static long getObjectSize(final Object object) {
        if (globalInstrumentation == null) {
            throw new IllegalStateException("Agent not initialized.");
        }
        return globalInstrumentation.getObjectSize(object);
    }

    public static void printObjectSize(Object object) {
        System.out.println("Object type: " + object.getClass()
                + ", size: " + getObjectSize(object) + " bytes");
    }

    public static void main(String[] args) {
        final String string = "Hello world!";
        System.err.println('"' + string + "\" size is equal to " + InstrumentationAgent.getObjectSize(string));
    }
}
