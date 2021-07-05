package ua.goit.jdbc;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.service.*;
import ua.goit.jdbc.view.ViewMessages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ConnectionManager cm = new ConnectionManager();
        ViewMessages view = new ViewMessages(cm);
        System.out.println(view.startMessage());

        Scanner scanner = new Scanner(System.in);

        try {

        } catch (InputMismatchException ex){
            System.out.println(view.errorMessage());
        }
    }
}
