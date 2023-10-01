package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswer;

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
    }

    private void run() {
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        Random random = new Random();
        boolean run = true;
        Scanner input = new Scanner(System.in);
        String answer;
        String phrase;
        boolean tumbler = true;
        while (run) {
            answer = input.nextLine();
            log.add(answer);
            System.out.println(answer);
            switch (answer.toLowerCase()) {
                case OUT: run = false;
                    break;
                case STOP: tumbler = false;
                    break;
                case CONTINUE: tumbler = true;
                default: if (tumbler) {
                    phrase = phrases.get(random.nextInt(phrases.size()));
                    log.add(phrase);
                    System.out.println(phrase);
                }
            }

        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswer))) {
            in.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/logs.txt", "data/phrases.txt");
        consoleChat.run();
    }
}
