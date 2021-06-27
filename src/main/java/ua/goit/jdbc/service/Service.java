package ua.goit.jdbc.service;

public interface Service<T> {
    String getAll();
    String getById(int id);
    void create(T entity);
    void delete(int id);
    void update(T entity);
}
