package ru.job4j.gc.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Только с абсолютным путем работает.
 * Пока не знаю как с относительным сделать.
 *
 * Если использовать
 * {@link java.nio.file.FileVisitor},
 * то в конечном итоге получается не
 * то что хотел/ожидал.
 *
 * С ридером вроде точно так же -
 * нужен только полынй путь.
 *
 * @author Constantine on 03.09.2022
 */
public class Test  {
    public static void main(String[] args) throws IOException {
        String absPath = "C:\\projects\\job4j_design\\data\\botPhrases.txt";
        /*Stream<String> stream = new BufferedReader(new FileReader("C:\\projects\\job4j_design\\data\\botPhrases.txt")).lines();
        Stream<String> stream = Files.lines(Path.of("C:\\projects\\job4j_design\\data\\botPhrases.txt"));
        Stream<String> stream = Files.lines(Paths.get("C:\\projects\\job4j_design\\data\\botPhrases.txt"));*/
        List<Path> source = Search.search(Path.of("C:\\projects"));
        Stream<String> stream = Files.lines(source.get(0));
        List<String> result  = stream.toList();
        stream.close();
        /*result.forEach(System.out::println);*/
        try (BufferedReader reader = new BufferedReader(new FileReader(absPath))) {
            reader.lines().forEach(System.out::println);
        }
    }
}
