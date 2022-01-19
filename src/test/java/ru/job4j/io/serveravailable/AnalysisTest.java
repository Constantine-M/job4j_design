package ru.job4j.io.serveravailable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Constantine on 17.01.2022
 */
public class AnalysisTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenServerUnavailableOnce() throws Exception {
        Analysis analysis = new Analysis();
            File source = folder.newFile("source.txt");
            File target = folder.newFile("target.txt");
            try (PrintWriter out = new PrintWriter(source)) {
                out.println("200 10:56:01");
                out.println("500 10:57:01");
                out.println("400 10:58:01");
                out.println("500 10:59:01");
                out.println("400 11:01:02");
                out.println("200 11:02:02");
            }
            analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
            StringBuilder result = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new FileReader(target))) {
                in.lines().forEach(result::append);
            }
            assertThat(result.toString(), is("10:57:01;11:02:02"));
    }

    @Test
    public void whenServerUnavailableTwice() throws Exception {
        Analysis analysis = new Analysis();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(result::add);
        }
        assertThat(result.get(0), is("10:57:01;10:59:01"));
        assertThat(result.get(1), is("11:01:02;11:02:02"));
    }
}