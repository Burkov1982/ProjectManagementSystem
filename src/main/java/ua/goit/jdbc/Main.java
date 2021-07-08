package ua.goit.jdbc;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.controller.Controller;
import ua.goit.jdbc.view.ConsoleManager;

public class Main {
    public static void main(String[] args) {
        ConnectionManager cm = new ConnectionManager();
        ConsoleManager consoleManager = new ConsoleManager(System.in, System.out);
        Controller controller = new Controller(consoleManager, cm);
        controller.doCommand();
    }
}
