package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.CustomerDAO;
import ua.goit.jdbc.dao.model.Customer;
import ua.goit.jdbc.dto.CustomerDTO;
import ua.goit.jdbc.view.ViewMessages;

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
        Customer customer = Converter.toCustomer(customerDTO);
        customerDAO.create(customer);
    }

    @Override
    public void delete(int id){
        customerDAO.delete(id);
    }

    @Override
    public void update(CustomerDTO customerDTO){
        Customer customer = Converter.toCustomer(customerDTO);
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
}
