package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Skill;
import ua.goit.jdbc.service.SkillService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class SkillDAO implements DataAccessObject<Skill> {
    private final ConnectionManager connectionManager;

    private static final String UPDATE = "UPDATE skills SET branch = ?, stage = ? WHERE skill_id = ?";
    private static final String DELETE = "DELETE FROM skills WHERE id = ?";
    private static final String INSERT = "INSERT INTO skills (branch, stage) VALUES (?, ?)";
    private static final String FIND_BY_ID = "SELECT skill_id, branch, stage FROM skills WHERE skill_id = ?";
    private static final String GET_ALL = "SELECT skill_id, branch, stage FROM skills";

    public SkillDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public LinkedList<Skill> getAll(){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)){
            return SkillService.toSkill(preparedStatement.executeQuery());
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<Skill> getAll(Skill entity) {
        return null;
    }

    @Override
    public Skill findById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setInt(1, id);
            return SkillService.toSkill(preparedStatement.executeQuery()).get(0);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Skill entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, entity.getBranch());
            preparedStatement.setString(2, entity.getStage());
            preparedStatement.executeQuery();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Skill entity) {
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setString(1, entity.getBranch());
            preparedStatement.setString(2, entity.getStage());
            preparedStatement.setInt(3, entity.getSkill_id());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Skill skill) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1, skill.getSkill_id());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
