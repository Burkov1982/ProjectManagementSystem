package ua.goit.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private String url;
    private Properties properties;
    public ConnectionManager(String host, String databasename, String username, String password){
        url = String.format("jdbc:postgresql://%s/%s", host, databasename);
        properties = enrichProperties(username, password);
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
            throw new RuntimeException(throwables);
        }
    }

    private Properties enrichProperties(String username, String password) {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        return properties;
    }
}
