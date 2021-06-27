package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Project;
import ua.goit.jdbc.service.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProjectDAO implements DataAccessObject<Project> {

    private static final String UPDATE = "UPDATE projects SET project_name = ?, project_description = ?," +
            "cost = ?, start_date = ? WHERE project_id = ?";
    private static final String DELETE = "DELETE FROM projects WHERE project_id = ?";
    private static final String INSERT = "INSERT INTO projects (project_name, project_description, cost, start_date)" +
            "VALUES (?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT project_id, project_name, project_description, cost, start_date" +
            " FROM projects";
    private static final String FIND_BY_ID = "SELECT project_id, project_name, project_description, cost, start_date" +
            " FROM projects WHERE project_id = ?";


    private final ConnectionManager connectionManager;

    public ProjectDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public LinkedList<Project> getAll(){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)){
            return Converter.toProject(preparedStatement.executeQuery());
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Project findById(Integer id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setInt(1, id);
            return Converter.toProject(preparedStatement.executeQuery()).get(0);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Project entity) {
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, entity.getProject_name());
            preparedStatement.setString(2, entity.getProject_description());
            preparedStatement.setInt(3, entity.getCost());
            preparedStatement.setDate(4, entity.getStart_date());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Project entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, entity.getProject_name());
            preparedStatement.setString(2, entity.getProject_description());
            preparedStatement.setInt(3, entity.getCost());
            preparedStatement.setDate(4, entity.getStart_date());
            preparedStatement.setInt(6, entity.getProject_id());
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
}
