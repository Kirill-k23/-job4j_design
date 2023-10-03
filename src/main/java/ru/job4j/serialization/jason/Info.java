package ru.job4j.serialization.jason;

public class Info {
    private final int number;
    private final String phone;

    public Info(int number, String phone) {
        this.number = number;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{"
                + "number=" + number
                + ", phone='" + phone + '\''
                + '}';
    }
}
