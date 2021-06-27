package ua.goit.jdbc.dao;

import java.util.LinkedList;

public interface DataAccessObject<T> {
    LinkedList<T> getAll();
    T findById(Integer id);
    void create(T entity);
    void update(T entity);
    void delete(Integer id);
}
