package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Customer;
import ua.goit.jdbc.service.CustomerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CustomerDAO implements DataAccessObject<Customer> {
    private final Connection connection;
    private final ConnectionManager connectionManager;

    private static final String UPDATE = "UPDATE customers SET customer_name = ? WHERE customer_id = ?";
    private static final String DELETE = "DELETE FROM customers WHERE customer_id = ?";
    private static final String INSERT = "INSERT INTO customers (customer_name) VALUES (?)";
    private static final String GET_ALL = "SELECT customer_id, customer_name FROM customers";
    private static final String FIND_BY_ID = "SELECT customer_id, customer_name FROM customers WHERE customer_id = ?";

    public CustomerDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        connection = connectionManager.getConnection();
    }

    @Override
    public LinkedList<Customer> getAll() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)){
            return CustomerService.toCustomer(preparedStatement.executeQuery());
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getAll(Customer entity) {
        return null;
    }

    @Override
    public Customer findById(Integer id) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            return CustomerService.toCustomer(preparedStatement.executeQuery()).get(0);
    }

    @Override
    public void create(Customer entity) throws SQLException {
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, entity.getCustomer_name());
            preparedStatement.executeUpdate();

    }

    @Override
    public void update(Customer entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, entity.getCustomer_name());
        preparedStatement.setInt(2, entity.getCustomer_id());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Customer customer) throws SQLException {
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, customer.getCustomer_id());
            preparedStatement.executeUpdate();
    }
}
