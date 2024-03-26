package org.example.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Command {
    @Value("${app.fullNameRegex}")
    private String fullNameRegex;
    @Value("${app.phoneRegex}")
    private String phoneRegex;
    @Value("${app.emailRegex}")
    private String emailRegex;
    @Value("${app.filenameRegex}")
    private String fileNameRegex;


    private CommandType commandType;
    private String parameter;

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public boolean commandParse(String input) {
        String command;
        String param = null;

        int i = input.indexOf(" ");
        if (i < 0) {
            command = input;
        } else {
            command = input.substring(0, i);
            param = input.substring(i + 1);
        }

        setCommandType(CommandType.ERROR);
        setParameter(null);

        switch (command.toUpperCase()) {
            case "ADD" -> {
                String[] params = param.split(";");
                if (params.length == 3) {
                    if (params[0].strip().matches(fullNameRegex) && params[1].strip().matches(phoneRegex) && params[2].strip().matches(emailRegex)) {
                        setCommandType(CommandType.ADD);
                        setParameter(param);
                        return true;
                    }
                }
            }
            case "DELETE" ->{
                if (param.matches(emailRegex)) {
                    setCommandType(CommandType.DELETE);
                    setParameter(param);
                    return true;
                }
            }
            case "SAVE" ->{
                if (param.matches(fileNameRegex)) {
                    setCommandType(CommandType.SAVE);
                    setParameter(param);
                    return true;
                }
            }
            case "PRINT" -> {
                setCommandType(CommandType.PRINT);
                return true;
            }
            case "EXIT" ->{
                setCommandType(CommandType.EXIT);
                return true;
            }
        }
        return false;
    }


}
