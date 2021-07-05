package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.CustomerDAO;
import ua.goit.jdbc.dao.model.Customer;
import ua.goit.jdbc.dto.CustomerDTO;
import ua.goit.jdbc.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CustomerService implements Service<CustomerDTO>{
    private ConnectionManager connectionManager;
    private CustomerDAO customerDAO;
    private ViewMessages viewMessages = new ViewMessages();

    public CustomerService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        customerDAO = new CustomerDAO(this.connectionManager);
    }

    @Override
    public void create(CustomerDTO customerDTO){
        Customer customer = toCustomer(customerDTO);
        customerDAO.create(customer);
    }

    @Override
    public void delete(CustomerDTO customerDTO){
        customerDAO.delete(toCustomer(customerDTO));
    }

    @Override
    public void update(CustomerDTO customerDTO){
        Customer customer = toCustomer(customerDTO);
        customerDAO.update(customer);
    }

    @Override
    public String getById(int id){
        return customerDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListCustomers(customerDAO.getAll());
    }

    @Override
    public String getAll(CustomerDTO entity) {
        return null;
    }

    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name());
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name());
    }

    public static LinkedList<Customer> toCustomer(ResultSet resultSet){
        try{
            LinkedList<Customer> customers = new LinkedList<>();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setCustomer_name(resultSet.getString("customer_name"));
                customers.addLast(customer);
            }
            return customers;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
