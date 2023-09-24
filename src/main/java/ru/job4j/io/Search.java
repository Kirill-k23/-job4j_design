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
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().startsWith("Simple")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }

    public static class SearchFiles extends SimpleFileVisitor<Path> {
        List<Path> list = new ArrayList<>();
        Predicate<Path> predicate;

        public SearchFiles(Predicate<Path> predicate) {
            this.predicate = predicate;
        }

        public List<Path> getList() {
            return list;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) {
            if (predicate.test(path)) {
                list.add(path);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

