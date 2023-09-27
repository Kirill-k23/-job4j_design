package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream Out = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                Out.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(path.toFile()))) {
                    Out.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validate(ArgsName name) {
        if (!Files.exists(Path.of(name.get("d")))) {
            throw new IllegalArgumentException(String.format(
                    "%s doesn't exist", name.get("d")));
        }
        if (!name.get("e").contains(".")) {
            throw new IllegalArgumentException(String.format("%s isn't correct"));
        }
        if (!name.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("%s must be '.zip'", name.get("o")));
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<Path> list = Search.search(Path.of(argsName.get("d")),
                f -> !f.toString().endsWith(argsName.get("e")));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.packFiles(list, new File(argsName.get("o")));
    }
}

