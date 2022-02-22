package ru.job4j.io.scanner.csv;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * Для теста используем временный файл.
 * В него будем записывать и его
 * будем читать. Почитать про ЭТО
 * можно по ссылке ниже:
 * {@see <https://junit.org/junit4/javadoc/4.9/org/junit/rules/TemporaryFolder.html>}.
 *
 * @author Constantine on 21.02.2022
 */
public class CSVReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Ignore
    @Test
    public void whenFilterTwoColumns() throws IOException {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsNameSc argsName = ArgsNameSc.of(new String[] {
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out="
                + target.getAbsolutePath(), "-filter=name,age"
                });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }
}