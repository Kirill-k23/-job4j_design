package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + " zipCode= " + zipCode
                + ", phone= '" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(233144, "+ 7 (918) 321-21-34");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream outputStream = new FileOutputStream(tempFile);
             ObjectOutputStream output = new ObjectOutputStream(outputStream)) {
            output.writeObject(contact);
        }
        try (FileInputStream inputStream = new FileInputStream(tempFile);
             ObjectInputStream stream = new ObjectInputStream(inputStream)) {
            final Contact contact1 = (Contact) stream.readObject();
            System.out.println(contact1);
        }
    }
}
