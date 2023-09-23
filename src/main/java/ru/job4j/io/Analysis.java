package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(source));
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(target));
        ) {
            String line;
            boolean con = false;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(" ");
                if (con != Integer.parseInt(strings[0]) >= 500) {
                    con = !con;
                    writer.append(strings[1]).append(";").append(con ? "" : System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
