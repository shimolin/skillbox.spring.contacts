package org.example;

import org.springframework.context.annotation.Profile;

@Profile("prod")
public class DataLoadProd implements DataLoad {
    @Override
    public void load(ContactsManager contactsManager) {
        System.out.println("DataLoadProd.load()");
    }
}
