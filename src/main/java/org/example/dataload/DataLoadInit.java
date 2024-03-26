package org.example.dataload;

import org.example.contactsmanager.ContactsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Profile("init")
public class DataLoadInit implements DataLoad {

    @Value("${app.fullNameRegex}")
    private String fullNameRegex;
    @Value("${app.phoneRegex}")
    private String phoneRegex;
    @Value("${app.emailRegex}")
    private String emailRegex;
    @Value("${app.contactsfilename}")
    private String filename;

    @Value("${app.env}")
    private String env;

    @Override
    public void load(ContactsManager contactsManager) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(";");
                if (params.length == 3) {
                    if (params[0].strip().matches(fullNameRegex) && params[1].strip().matches(phoneRegex) && params[2].strip().matches(emailRegex)) {
                        contactsManager.addContasct(params[0].strip(), params[1].strip(), params[2].strip());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
}
