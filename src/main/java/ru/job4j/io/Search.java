package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().startsWith("Simple")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }

    public static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder isn't correct. Usage  ROOT_FOLDER.");
        }
        Path start = Paths.get(args[0]);
        if (!Files.exists(start)) {
            throw new IllegalArgumentException(String.format("%s doesn't exist", start.toAbsolutePath()));
        }
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException(String.format("%s isn't a directory", start.toAbsolutePath()));
        }
    }
}

