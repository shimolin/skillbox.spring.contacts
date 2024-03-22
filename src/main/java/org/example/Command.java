package org.example;
public class Command {
    private CommandType commandType;
    private String parameter;

    public Command(CommandType commandType, String parameter) {
        this.commandType = commandType;
        this.parameter = parameter;
    }

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

}
