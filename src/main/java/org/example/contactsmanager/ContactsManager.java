package org.example.contactsmanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContactsManager {

    @Value("${app.pathtosave}")
    private String pathToSave;
    private Map<String, Contact> contacts = new HashMap<>(); // key=email

    public ContactsManager() {
    }

    public boolean addContasct(String fullName, String phoneNumber, String email) {
        Contact contact = contacts.put(email, new Contact(fullName, phoneNumber, email));
        return contact == null;
    }

    public boolean deleteContactByEmail(String email) {
        Contact contact = contacts.remove(email);
        return contact != null;
    }

    public boolean saveContactsToFile(String fileName) {
        File file = new File(pathToSave+fileName);
        StringBuilder stringBuilder = new StringBuilder();
        contacts.forEach((e, c) -> stringBuilder.append(c.getFullName()).append(";").append(c.getPhoneNumber()).append(";").append(e).append("\n"));
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void printAllContacts() {
        for (Contact c : contacts.values()) {
            System.out.println(c.getFullName() + " | " + c.getPhoneNumber() + " | " + c.getEmail());
        }
    }
}
