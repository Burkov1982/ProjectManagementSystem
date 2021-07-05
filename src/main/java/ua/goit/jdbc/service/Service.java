package ua.goit.jdbc.service;

public interface Service<T> {
    String getAll();
    String getAll(T entity);
    String getById(int id);
    void create(T entity);
    void delete(T entity);
    void update(T entity);
}
