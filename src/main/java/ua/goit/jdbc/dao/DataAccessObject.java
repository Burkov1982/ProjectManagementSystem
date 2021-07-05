package ua.goit.jdbc.dao;

import java.util.LinkedList;

public interface DataAccessObject<T> {
    LinkedList<T> getAll();
    LinkedList<T> getAll(T entity);
    T findById(Integer id);
    void create(T entity);
    void update(T entity);
    void delete(T entity);
}
