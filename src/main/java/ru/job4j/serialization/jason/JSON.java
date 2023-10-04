package ru.job4j.serialization.jason;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class JSON {
    public static void main(String[] args) {
        JSONObject jsonInfo = new JSONObject(
                "{\"number\":123,\"phone\":\"+7 923-213-12-32\"}");
        JSONArray jsonArray = new JSONArray(Arrays.asList("Petr", "Rosa"));
        final Client client = new Client("Ivan", "Ivanov",
                false, 35, new Info(123, "+7 923-213-12-32"),
                new String[]{"Petr", "Vera"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", client.getName());
        jsonObject.put("surname", client.getSurname());
        jsonObject.put("sex", client.isSex());
        jsonObject.put("age", client.getAge());
        jsonObject.put("info", jsonInfo);
        jsonObject.put("children", jsonArray);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(client));
    }
}
