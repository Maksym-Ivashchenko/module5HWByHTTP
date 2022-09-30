package ua.goit.commands;

public interface Command {
    boolean canExecute(String input);

    void execute();
}


