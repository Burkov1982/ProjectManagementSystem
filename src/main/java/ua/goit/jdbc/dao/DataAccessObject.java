package ua.goit.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public interface DataAccessObject<T> {
    LinkedList<T> getAll();
    ResultSet getAll(T entity) throws SQLException;
    T findById(Integer id) throws SQLException;
    void create(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
}
