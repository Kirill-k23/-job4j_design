package ru.job4j.io.duplicaters;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
   private Map<FileProperty, List<Path>> visitor = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        visitor.putIfAbsent(fileProperty, new ArrayList<>());
        visitor.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public void search() {
        for (FileProperty key : visitor.keySet()) {
            if (visitor.get(key).size() > 1) {
                System.out.println(key.getName());
                System.out.println(key.getSize());
                visitor.get(key).forEach(System.out::println);
            }
        }
    }
}
