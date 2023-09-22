package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String red;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(this.path))) {
            while ((red = reader.readLine()) != null) {
                if (!red.isBlank() && !red.startsWith("#")) {
                    if (!red.contains("=")) {
                        throw new IllegalArgumentException(String.format(" Line contains an invalid template %s", red));
                    }

                    String[] lines = red.split("=", 2);
                    if ((lines[0].isBlank() || lines[1].isBlank())) {
                        throw new IllegalArgumentException(String.format(" Line contains an invalid template %s", red));
                    }
                    values.put(lines[0], lines[1]);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.get(key) == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}