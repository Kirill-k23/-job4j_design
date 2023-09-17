package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/even.txt")) {
            StringBuilder even = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                even.append((char) read);
            }
            String[] lines = even.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line);
                }
            }
            System.out.println(even);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
