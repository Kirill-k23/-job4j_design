package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void test(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (
                PrintWriter out = new PrintWriter(source)
        ) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File taget = tempDir.resolve("Analysis.txt").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), taget.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (
                BufferedReader in = new BufferedReader(new FileReader(taget))
        ) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:58:01;11:01:02;11:02:02;").hasToString(rsl.toString());
    }
}