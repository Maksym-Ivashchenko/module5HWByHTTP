package ua.goit.controller;

import ua.goit.commands.Command;
import ua.goit.exceptions.ExitException;
import ua.goit.view.View;

import java.util.List;

public class Controller {
    private final View view;
    private final List<Command> commands;

    public Controller(View view, List<Command> commands) {
        this.view = view;
        this.commands = commands;
    }


    public void run() {
        view.write("Hello, please enter help to see all command");
        try {
            execute();
        } catch (ExitException e) {}
    }

    private void execute() {
        while (true) {
            String input = view.read();
            boolean isInputCorrect = false;
            for (Command command : commands) {
                if (command.canExecute(input)) {

                    command.execute();
                    isInputCorrect = true;
                }
            }
            if (!isInputCorrect) {
                view.write("Command not found. Please enter help to see all commands");
            }
        }
    }
}
