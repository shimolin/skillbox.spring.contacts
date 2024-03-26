package org.example;

import org.example.command.Command;
import org.example.command.CommandType;
import org.example.contactsmanager.ContactsManager;
import org.example.dataload.DataLoad;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Application {
    private final ContactsManager contactsManager;
    private final DataLoad dataLoad;

    private final Command command;

    public Application(ContactsManager contactsManager, DataLoad loadData, Command command) {
        this.contactsManager = contactsManager;
        this.dataLoad = loadData;
        this.command = command;
    }

    public void run(){
        dataLoad.load(contactsManager);
        printMenu();
        do {
            boolean result;
            System.out.print("-> ");
            String input = (new Scanner(System.in)).nextLine();
            command.commandParse(input);
            switch (command.getCommandType()){
                case ADD :
                    String[] params = command.getParameter().split(";");
                    result = contactsManager.addContasct(params[0].strip(), params[1].strip(), params[2].strip());
                    System.out.println(result ? "Контакт успешно добавлен" : "Контакт уже существует");
                    break;
                case DELETE:
                    result = contactsManager.deleteContactByEmail(command.getParameter());
                    System.out.println(result ? "Контакт успешно удален" : "Контакт не найден");
                    break;
                case SAVE:
                    result = contactsManager.saveContactsToFile(command.getParameter());
                    System.out.println(result ? "Файл успешно сохранен" : "Ошибка сохранения в файл");
                    break;
                case PRINT:
                    contactsManager.printAllContacts();
                    break;
                case ERROR:
                    System.out.println("Ошибка синтаксиса команды!!!");
                    break;
            }
        } while (command.getCommandType() != CommandType.EXIT);
    }

    private void printMenu() {
        System.out.println("Введите команду:");
        System.out.println("PRINT");
        System.out.println("ADD <Ф. И. О.; номер телефона; email>");
        System.out.println("DELETE <email>");
        System.out.println("SAVE <имя файла>");
        System.out.println("EXIT");
    }

}


