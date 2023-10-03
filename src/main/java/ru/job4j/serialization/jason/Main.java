package ru.job4j.serialization.jason;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Client client = new Client("Ivan", "Ivanov",
                false, 35, new Info(123, "+7 923-213-12-32"),
                new String[] {"Petr", "Vera"});
        final Gson gson = new GsonBuilder().create();
        final String clientJson = gson.toJson(client);
        System.out.println(gson.toJson(client));
        final Client client1 = gson.fromJson(clientJson, Client.class);
        System.out.println(client1);
    }
}
