package ua.goit.jdbc.service;

import java.sql.SQLException;

public interface Service<T> {
    String getAll();
    String getAll(T entity) throws SQLException;
    String getById(int id) throws SQLException;
    void create(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    void update(T entity) throws SQLException;
}
