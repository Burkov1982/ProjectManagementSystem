package ua.goit.jdbc.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private String url;
    private Properties properties;

    public ConnectionManager(){
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url");
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
            throw new RuntimeException(throwables);
        }
    }
}
