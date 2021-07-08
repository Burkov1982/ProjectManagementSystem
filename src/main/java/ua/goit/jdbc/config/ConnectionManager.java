package ua.goit.jdbc.config;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private final Properties properties = new Properties();

    public ConnectionManager(){
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/database.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(properties.getProperty("db.host"), properties.getProperty("db.login"),
                    properties.getProperty("db.password"));
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
            throw new RuntimeException(throwables);
        }
    }
}
