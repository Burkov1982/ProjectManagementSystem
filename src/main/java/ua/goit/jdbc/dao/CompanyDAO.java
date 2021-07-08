package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Company;
import ua.goit.jdbc.service.CompanyService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private final Connection connection;

    public CompanyDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        connection = connectionManager.getConnection();
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
    public ResultSet getAll(Company entity) {
        return null;
    }

    @Override
    public Company findById(Integer id) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID);
            preparedStatement.setInt(1, id);
            return CompanyService.toCompany(preparedStatement.executeQuery()).get(0);
    }

    @Override
    public void create(Company entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, entity.getCompany_name());
        preparedStatement.setString(2, entity.getHeadquarters());
        preparedStatement.executeUpdate();
    }


    @Override
    public void update(Company entity) throws SQLException {
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
                 preparedStatement.setString(1, entity.getCompany_name());
                 preparedStatement.setString(2, entity.getHeadquarters());
                 preparedStatement.setInt(3, entity.getCompany_id());
                 preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Company company) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, company.getCompany_id());
            preparedStatement.executeUpdate();
    }
}
