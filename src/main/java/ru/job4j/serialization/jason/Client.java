package ru.job4j.serialization.jason;

import java.util.Arrays;

public class Client {
    private final String name;
    private final String surname;
    private final boolean sex;
    private final int age;
    private final Info info;
    private final String[] children;

    public Client(String name, String surname, boolean sex,
                  int age, Info info, String[] children) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.info = info;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Client{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", sex=" + sex
                + ", age=" + age
                + ", info=" + info
                + ", children=" + Arrays.toString(children)
                + '}';
    }
}
