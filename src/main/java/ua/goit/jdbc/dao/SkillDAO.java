package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Skill;
import ua.goit.jdbc.service.SkillService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SkillDAO implements DataAccessObject<Skill> {
    private final Connection connection;

    private static final String UPDATE = "UPDATE skills SET branch = ?, stage = ? WHERE skill_id = ?";
    private static final String DELETE = "DELETE FROM skills WHERE id = ?";
    private static final String INSERT = "INSERT INTO skills (branch, stage) VALUES (?, ?)";
    private static final String FIND_BY_ID = "SELECT skill_id, branch, stage FROM skills WHERE skill_id = ?";
    private static final String GET_ALL = "SELECT skill_id, branch, stage FROM skills";

    public SkillDAO(ConnectionManager connectionManager) {
        connection = connectionManager.getConnection();
    }

    @Override
    public LinkedList<Skill> getAll(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_ALL);
            return SkillService.toSkill(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getAll(Skill entity) {
        return null;
    }

    @Override
    public Skill findById(Integer id) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            return SkillService.toSkill(preparedStatement.executeQuery()).get(0);
    }

    @Override
    public void create(Skill entity) throws SQLException {
          PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
          preparedStatement.setString(1, entity.getBranch());
          preparedStatement.setString(2, entity.getStage());
          preparedStatement.executeQuery();
    }


    @Override
    public void update(Skill entity) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, entity.getBranch());
            preparedStatement.setString(2, entity.getStage());
            preparedStatement.setInt(3, entity.getSkill_id());
            preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Skill skill) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, skill.getSkill_id());
            preparedStatement.executeUpdate();
    }
}
