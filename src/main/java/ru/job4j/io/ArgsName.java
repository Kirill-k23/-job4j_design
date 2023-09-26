package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format(
                    "This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (var s : args) {
            validate(s);
            String[] str = s.split("=", 2);
            values.put(str[0].substring(1), str[1]);
        }
    }

    private void validate(String str) {
        if (!str.contains("=")) {
            throw new IllegalArgumentException(String.format(
                    "'%s' doesn't contain '=' ", str));
        }

        if (!str.startsWith("-")) {
            throw new IllegalArgumentException(String.format(
                    "'%s' doesn't contain '-' ", str));
        }
        if (str.startsWith("-") && str.charAt(1) == '=') {
            throw new IllegalArgumentException(
                    String.format("'%s' doesn't contain a key", str));
        }
        if (str.indexOf('=') == str.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("'%s' doesn't contain a value", str));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}