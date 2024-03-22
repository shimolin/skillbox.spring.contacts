package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Application {

    @Value("${app.fullNameRegex}")
    private String fullNameRegex;
    @Value("${app.phoneRegex}")
    private String phoneRegex;
    @Value("${app.emailRegex}")
    private String emailRegex;
    @Value("${app.filenameRegex}")
    private String filenameRegex;

    private final ContactsManager contactsManager;
    private final DataLoad dataLoad;

    public Application(ContactsManager contactsManager, DataLoad loadData) {
        this.contactsManager = contactsManager;
        this.dataLoad = loadData;
    }

    public void run(){
        dataLoad.load(contactsManager);
        printMenu();
        Command command;
        do {
            boolean result;
            System.out.print("-> ");
            command = getCommand();
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

    public Command getCommand() {
        String input = (new Scanner(System.in)).nextLine();

        String command;
        String param = null;

        int i = input.indexOf(" ");
        if (i < 0) {
            command = input;
        } else {
            command = input.substring(0, i);
            param = input.substring(i + 1);
        }

        switch (command.toUpperCase()) {
            case "ADD" -> {
                String[] params = param.split(";");
                if (params.length == 3) {
                    if (params[0].strip().matches(fullNameRegex) && params[1].strip().matches(phoneRegex) && params[2].strip().matches(emailRegex)) {
                        return new Command(CommandType.ADD, param);
                    }
                } else return new Command(CommandType.ERROR, null);
            }
            case "DELETE" ->{
                if (param.matches(emailRegex)) {
                    return new Command(CommandType.DELETE, param);
                } else return new Command(CommandType.ERROR, null);
            }
            case "SAVE" ->{
                if (param.matches(filenameRegex)) {
                    return new Command(CommandType.SAVE, param);
                } else return new Command(CommandType.ERROR, null);
            }
            case "PRINT" -> {
                return new Command(CommandType.PRINT, null);
            }
            case "EXIT" ->{
                return new Command(CommandType.EXIT, null);
            }
        }
        return new Command(CommandType.ERROR, null);
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


