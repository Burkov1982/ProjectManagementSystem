package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Developer;
import ua.goit.jdbc.service.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DeveloperDAO implements DataAccessObject<Developer> {
    private ConnectionManager connectionManager;

    private static final String UPDATE = "UPDATE developers SET first_name = ?, last_name = ?, gender = ?, salary = ?" +
            "WHERE developer_id = ?";
    private static final String DELETE = "DELETE FROM developers WHERE developer_id = ?";
    private static final String GET_ALL = "SELECT developer_id, first_name, last_name, gender, salary FROM developers";
    private static final String INSERT = "INSERT INTO developers (first_name, last_name, gender, salary) " +
            "VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT developer_id, first_name, last_name, gender, salary FROM developers " +
            "WHERE developer_id = ?";
    private static final String GET_DEVELOPERS_BY_STAGE = "SELECT developer_id, first_name, last_name, gender, salary" +
            " FROM developers WHERE developer_id IN (SELECT developer_id FROM developer_skills WHERE skill_id IN" +
            "(SELECT skill_id FROM skills WHERE stage = ?))";
    private static final String GET_DEVELOPERS_BY_BRANCH = "SELECT developer_id, first_name, last_name, gender, salary" +
            " FROM developers WHERE developer_id IN (SELECT developer_id FROM developer_skills WHERE skill_id IN" +
            "(SELECT skill_id FROM skills WHERE branch = ?))";
    private static final String SUM_OF_SAL_BY_PROJ_ID = "SELECT SUM(d.salary) FROM developers d WHERE d.developer_id " +
            "IN (SELECT developer_id FROM project_developers WHERE project_id = ?)";
    private static final String GET_DEVELOPERS_OF_PROJECT = "SELECT developer_id, first_name, last_name, gender, salary " +
            "FROM developers WHERE developer_id IN (SELECT developer_id FROM project_developers WHERE project_id = ?);";

    public DeveloperDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public LinkedList<Developer> getDevelopersByBranch(String branch){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DEVELOPERS_BY_BRANCH)){
            preparedStatement.setString(1, branch);
            return Converter.toDeveloper(preparedStatement.executeQuery());
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public LinkedList<Developer> getDevelopersOfProjectById(Integer id){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DEVELOPERS_OF_PROJECT)){
            preparedStatement.setInt(1, id);
            return Converter.toDeveloper(preparedStatement.executeQuery());
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public Integer getSumOfDevSelByProjId(Integer id){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SUM_OF_SAL_BY_PROJ_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("sum");
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<Developer> getAll() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)){
            return Converter.toDeveloper(preparedStatement.executeQuery());
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Developer findById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
                 preparedStatement.setInt(1, id);
                 return Converter.toDeveloper(preparedStatement.executeQuery()).get(0);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Developer entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, entity.getFirst_name());
            preparedStatement.setString(2, entity.getLast_name());
            preparedStatement.setString(3, entity.getGender());
            preparedStatement.setInt(4, entity.getSalary());
            preparedStatement.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Developer entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, entity.getFirst_name());
            preparedStatement.setString(2, entity.getLast_name());
            preparedStatement.setString(3, entity.getGender());
            preparedStatement.setInt(4, entity.getSalary());
            preparedStatement.setInt(5, entity.getDeveloper_id());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public LinkedList<Developer> getDevelopersByStage(String stage) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DEVELOPERS_BY_STAGE)){
            preparedStatement.setString(1, stage);
            return Converter.toDeveloper(preparedStatement.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
