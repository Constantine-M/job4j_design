package ru.job4j.io.scanner.csv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 7. Scanner.
 *
 * Данный класс описывает принципы
 * работы с файлами формата CSV.
 *
 * @author Constantine on 21.02.2022
 */
public class CSVReader {

    public static void handle(ArgsNameSc argsName) {
        Path source = Paths.get(argsName.get("path"));
        Path target = Paths.get(argsName.get("out"));
        try (Scanner scanner = new Scanner(source)) {
            scanner.useDelimiter(";");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Path source = Paths.get("./data/source1.csv");
        Scanner scanner = new Scanner(source, StandardCharsets.UTF_8);
        scanner.useDelimiter(";");
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine() + "|");
        }
        /*try (Scanner scanner = new Scanner(source, StandardCharsets.UTF_8)) {
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
