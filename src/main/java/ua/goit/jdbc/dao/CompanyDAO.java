package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Company;
import ua.goit.jdbc.service.CompanyService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class CompanyDAO  implements DataAccessObject<Company>{

    private static final String DELETE = "DELETE FROM companies WHERE company_id = ?";
    private static final String UPDATE = "UPDATE companies SET company_name = ?, headquarters = ? WHERE company_id = ?";
    private static final String GET_ALL = "SELECT company_id, company_name, headquarters FROM companies";
    private static final String INSERT = "INSERT INTO companies (company_name, headquarters) " +
            "VALUES (?, ?)";
    private static final String SELECT_COMPANY_BY_ID = "SELECT company_id, company_name, headquarters FROM companies " +
            "WHERE company_id = ?";

    private final ConnectionManager connectionManager;

    public CompanyDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public LinkedList<Company> getAll() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)){
            return CompanyService.toCompany(preparedStatement.executeQuery());
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<Company> getAll(Company entity) {
        return null;
    }

    @Override
    public Company findById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID)){
            preparedStatement.setInt(1, id);
            return CompanyService.toCompany(preparedStatement.executeQuery()).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Company entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, entity.getCompany_name());
            preparedStatement.setString(2, entity.getHeadquarters());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Company entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
                 preparedStatement.setString(1, entity.getCompany_name());
                 preparedStatement.setString(2, entity.getHeadquarters());
                 preparedStatement.setInt(3, entity.getCompany_id());
                 preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Company company) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1, company.getCompany_id());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
