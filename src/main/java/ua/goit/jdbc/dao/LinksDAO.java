package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Link;
import ua.goit.jdbc.service.LinkService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LinksDAO implements DataAccessObject<Link>{


    private static final String INSERT_CUSTOMER_COMPANIES = "INSERT INTO customers_companies " +
            "(customer_id, company_id, project_id) VALUES (?, ?, ?)";
    private static final String INSERT_PROJECT_DEVELOPERS = "INSERT INTO project_developers" +
            "(project_id, developer_id) VALUES (?, ?)";
    private static final String INSERT_DEVELOPER_SKILLS = "INSERT INTO developer_skills (skill_id, developer_id) VALUES" +
            "(?, ?)";

    private static final String DELETE_CUSTOMER_COMPANIES = "DELETE FROM customers_companies WHERE " +
            "customer_id = ? AND company_id = ? AND project_id = ?";
    private static final String DELETE_PROJECT_DEVELOPERS = "DELETE FROM project_developers WHERE " +
            "project_id = ? AND developer_id = ?";
    private static final String DELETE_DEVELOPER_SKILLS = "DELETE FROM developer_skills WHERE " +
            "skill_id = ? AND developer_id = ?";

    private static final String UPDATE_CUSTOMER_COMPANIES = "UPDATE customers_companies " +
            "SET customer_id = ?, company_id = ?, project_id = ? " +
            "WHERE customer_id = ? AND company_id = ? AND project_id = ?";
    private static final String UPDATE_PROJECT_DEVELOPERS = "UPDATE project_developers " +
            "SET project_id = ?, developer_id = ? " +
            "WHERE project_id = ? AND developer_id = ?";
    private static final String UPDATE_DEVELOPER_SKILLS = "UPDATE developer_skills " +
            "SET skill_id = ?, developer_id = ? " +
            "WHERE Skill_id = ? AND developer_id = ?";

    private static final String SELECT_CUSTOMERS_COMPANIES = "SELECT customer_id, company_id, project_id" +
            " FROM customers_companies";
    private static final String SELECT_PROJECT_DEVELOPERS = "SELECT project_id, developer_id FROM project_developers";
    private static final String SELECT_DEVELOPER_SKILLS = "SELECT skill_id, developer_id FROM developer_skills";

    private final Connection connection;
    private final LinkService linkService = new LinkService();

    public LinksDAO(ConnectionManager connectionManager) {
        connection = connectionManager.getConnection();
    }

    @Override
    public LinkedList<Link> getAll() {
        return null;
    }

    @Override
    public ResultSet getAll(Link entity) throws SQLException {
        String table = entity.getTable();

            if (table.equalsIgnoreCase("customers_companies")){
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS_COMPANIES);
                return preparedStatement.executeQuery();
            }
            if (table.equalsIgnoreCase("project_developers")){
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_DEVELOPERS);
                return preparedStatement.executeQuery();

            }
            if (table.equalsIgnoreCase("developer_skills")){
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPER_SKILLS);
                return preparedStatement.executeQuery();
            }
            return null;
    }

    @Override
    public Link findById(Integer id) {
        return null;
    }

    @Override
    public void create(Link entity) throws SQLException {
            String table = entity.getTable();
            Integer project_id = entity.getProject_id();
            Integer customer_id = entity.getCustomer_id();
            Integer company_id = entity.getCompany_id();
            Integer developer_id = entity.getDeveloper_id();
            Integer skill_id = entity.getSkill_id();

            if (table.equalsIgnoreCase("customers_companies")){
                try  (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_COMPANIES)){
                    preparedStatement.setInt(1, customer_id);
                    preparedStatement.setInt(2, company_id);
                    preparedStatement.setInt(3, project_id);
                    preparedStatement.executeUpdate();   
                }
            } else if (table.equalsIgnoreCase("project_developers")){
                try  (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_DEVELOPERS)){
                    preparedStatement.setInt(1, project_id);
                    preparedStatement.setInt(2, developer_id);
                    preparedStatement.executeUpdate();
                }
            } else if (table.equalsIgnoreCase("developer_skills")){
                try  (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEVELOPER_SKILLS)){
                    preparedStatement.setInt(1, skill_id);
                    preparedStatement.setInt(2, developer_id);
                    preparedStatement.executeUpdate();
                }
            }
    }

    public void update(Link entity, Link oldEntity) throws SQLException {
        String table = entity.getTable();

        Integer project_id = entity.getProject_id();
        Integer customer_id = entity.getCustomer_id();
        Integer company_id = entity.getCompany_id();
        Integer developer_id = entity.getDeveloper_id();
        Integer skill_id = entity.getSkill_id();

        Integer old_project_id = oldEntity.getProject_id();
        Integer old_customer_id = oldEntity.getCustomer_id();
        Integer old_company_id = oldEntity.getCompany_id();
        Integer old_developer_id = oldEntity.getDeveloper_id();
        Integer old_skill_id = oldEntity.getSkill_id();


            if (table.equalsIgnoreCase("customers_companies")){
                try  (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_COMPANIES)){
                    preparedStatement.setInt(1, customer_id);
                    preparedStatement.setInt(2, company_id);
                    preparedStatement.setInt(3, project_id);

                    preparedStatement.setInt(4, old_customer_id);
                    preparedStatement.setInt(5, old_company_id);
                    preparedStatement.setInt(6, old_project_id);

                    preparedStatement.executeUpdate();
                }
            } else if (table.equalsIgnoreCase("project_developers")){
                try  (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROJECT_DEVELOPERS)){
                    preparedStatement.setInt(1, project_id);
                    preparedStatement.setInt(2, developer_id);

                    preparedStatement.setInt(3, old_project_id);
                    preparedStatement.setInt(4, old_developer_id);
                    preparedStatement.executeUpdate();
                }
            } else if (table.equalsIgnoreCase("developer_skills")){
                try  (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVELOPER_SKILLS)){
                    preparedStatement.setInt(1, skill_id);
                    preparedStatement.setInt(2, developer_id);

                    preparedStatement.setInt(3, old_skill_id);
                    preparedStatement.setInt(4, old_developer_id);
                    preparedStatement.executeUpdate();
                }
            }
    }

    @Override
    public void update(Link entity) {}

    @Override
    public void delete(Link entity) throws SQLException {

        String table = entity.getTable();
        Integer project_id = entity.getProject_id();
        Integer customer_id = entity.getCustomer_id();
        Integer company_id = entity.getCompany_id();
        Integer developer_id = entity.getDeveloper_id();
        Integer skill_id = entity.getSkill_id();

        if (table.equalsIgnoreCase("customers_companies")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_COMPANIES)) {
                preparedStatement.setInt(1, customer_id);
                preparedStatement.setInt(2, company_id);
                preparedStatement.setInt(3, project_id);
                preparedStatement.executeUpdate();
            }
        } else if (table.equalsIgnoreCase("project_developers")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJECT_DEVELOPERS)) {
                preparedStatement.setInt(1, project_id);
                preparedStatement.setInt(2, developer_id);
                preparedStatement.executeUpdate();
            }
        } else if (table.equalsIgnoreCase("developer_skills")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEVELOPER_SKILLS)) {
                preparedStatement.setInt(1, skill_id);
                preparedStatement.setInt(2, developer_id);
                preparedStatement.executeUpdate();
            }
        }
    }
}

