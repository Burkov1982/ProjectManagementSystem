package ua.goit.jdbc.controller;

import ua.goit.jdbc.command.*;
import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.view.ConsoleManager;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private final ConsoleManager consoleManager;
    private final ArrayList<Command> commands;
    private final UtilMessages utilMessages;
    public Controller(ConsoleManager consoleManager, ConnectionManager connectionManager) {
        this.consoleManager = consoleManager;
        this.commands = new ArrayList<>(Arrays.asList(new Create(consoleManager, connectionManager), new Select(consoleManager, connectionManager),
                new Update(consoleManager, connectionManager), new Delete(consoleManager, connectionManager)));
        utilMessages = new UtilMessages(consoleManager);
    }

    public void doCommand() {
        boolean running = true;
        consoleManager.write("Welcome to ProjectManagementSystem!");
        while (running) {
            utilMessages.menuMessage();
            String inputCommand = consoleManager.read();
            for (Command command : commands) {
                if (command.canProcess(inputCommand)) {
                    command.process();
                    break;
                } else if (inputCommand.equalsIgnoreCase("exit")) {
                    consoleManager.write("Good bye!");
                    running = false;
                    break;
                }
            }
        }
    }
}
