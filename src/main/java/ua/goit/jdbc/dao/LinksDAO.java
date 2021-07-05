package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.Link;
import ua.goit.jdbc.service.LinkService;
import ua.goit.jdbc.view.ViewMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LinksDAO implements DataAccessObject<Link>{

    private static final String DELETE_LINK = "DELETE FROM ? WHERE ?";
    private static final String UPDATE_LINK = "UPDATE ? SET ? WHERE ?";
    private static final String INSERT_LINK_PATTERN = "INSERT INTO ? (?) VALUES (?)";
    private static final String CUSTOMER_COMPANIES = "%s, %s, %s";
    private static final String PROJECT_DEVELOPERS = "%s, %s";
    private static final String DEVELOPER_SKILLS = "%s, %s";
    private static final String GET_ALL_PATTERN = "SELECT ? FROM ?";

    private final ConnectionManager cm;
    private final LinkService linkService;
    private final ViewMessages viewMessages = new ViewMessages();

    public LinksDAO(ConnectionManager connectionManager) {
        cm = connectionManager;
        linkService = new LinkService(cm);
    }

    @Override
    public LinkedList<Link> getAll() {
        return null;
    }

    @Override
    public LinkedList<Link> getAll(Link entity) {
        try (Connection connection = cm.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PATTERN)){

            String table = entity.getTable();
            Integer project_id = entity.getProject_id();
            Integer customer_id = entity.getCustomer_id();
            Integer company_id = entity.getCompany_id();
            Integer developer_id = entity.getDeveloper_id();
            Integer skill_id = entity.getSkill_id();

            if (table.toLowerCase().equals("customer_companies") && customer_id!=null
                    && company_id!=null && project_id!=null){
                preparedStatement.setString(2, table.toLowerCase());
                preparedStatement.setString(1, String.format(CUSTOMER_COMPANIES,
                        "customer_id", "company_id", "project_id"));
                ResultSet resultSet = preparedStatement.executeQuery();
                return linkService.toLink(resultSet, entity.getTable());

            } else if (table.toLowerCase().equals("project_developers") && project_id!=null
                    && developer_id!=null){
                preparedStatement.setString(2, table.toLowerCase());
                preparedStatement.setString(1, String.format(PROJECT_DEVELOPERS,
                        "project_id", "developer_id"));
                ResultSet resultSet = preparedStatement.executeQuery();
                return  linkService.toLink(resultSet, entity.getTable());

            } else if (table.toLowerCase().equals("developer_skills") && developer_id!=null && skill_id!=null){
                preparedStatement.setString(2, table.toLowerCase());
                preparedStatement.setString(1, String.format(DEVELOPER_SKILLS,
                        "skill_id", "developer_id"));
                ResultSet resultSet = preparedStatement.executeQuery();
                return linkService.toLink(resultSet, entity.getTable());

            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Link findById(Integer id) {
        return null;
    }

    @Override
    public void create(Link entity) {
        try (Connection connection = cm.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINK_PATTERN)){

            String table = entity.getTable();
            Integer project_id = entity.getProject_id();
            Integer customer_id = entity.getCustomer_id();
            Integer company_id = entity.getCompany_id();
            Integer developer_id = entity.getDeveloper_id();
            Integer skill_id = entity.getSkill_id();

            if (table.toLowerCase().equals("customer_companies") && customer_id!=null
                    && company_id!=null && project_id!=null){
                preparedStatement.setString(1, table.toLowerCase());
                preparedStatement.setString(2, String.format(CUSTOMER_COMPANIES,
                        "customer_id", "company_id", "project_id"));
                preparedStatement.setString(3, String.format(CUSTOMER_COMPANIES,
                        customer_id, company_id, project_id));
                preparedStatement.executeUpdate();
            } else if (table.toLowerCase().equals("project_developers") && project_id!=null
                    && developer_id!=null){
                preparedStatement.setString(1, table.toLowerCase());
                preparedStatement.setString(2, String.format(PROJECT_DEVELOPERS,
                        "project_id", "developer_id"));
                preparedStatement.setString(3, String.format(PROJECT_DEVELOPERS,
                        project_id, developer_id));
                preparedStatement.executeUpdate();
            } else if (table.toLowerCase().equals("developer_skills") && developer_id!=null && skill_id!=null){
                preparedStatement.setString(1, table.toLowerCase());
                preparedStatement.setString(2, String.format(DEVELOPER_SKILLS,
                        "skill_id", "developer_id"));
                preparedStatement.setString(3, String.format(DEVELOPER_SKILLS,
                        skill_id, developer_id));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Link entity) {
        try (Connection connection = cm.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LINK)){
            String table = entity.getTable();
            switch (table){
                case "customer_companies":
                    preparedStatement.setString(1, table);
                    preparedStatement.setString(2, String.format(CUSTOMER_COMPANIES,
                            "customer_id = "+entity.getCustomer_id()+", ", "company_id = "+entity.getCompany_id()+", ",
                            "project_id = "+entity.getProject_id()));
                    preparedStatement.setString(3, String.format("%s, %s",
                            "company_id = "+entity.getCompany_id()+", ", "project_id = "+entity.getProject_id()+", "));
                    preparedStatement.executeUpdate();
                    break;
                case "project_developers":
                    preparedStatement.setString(1, table);
                    preparedStatement.setString(2, String.format(PROJECT_DEVELOPERS,
                            "project_id = "+entity.getProject_id()+", ", "developer_id = "+entity.getDeveloper_id()));
                    preparedStatement.setString(3, "developer_id = "+entity.getDeveloper_id());
                    preparedStatement.executeUpdate();
                    break;
                case "developer_skills":
                    preparedStatement.setString(1, table);
                    preparedStatement.setString(2, String.format(DEVELOPER_SKILLS,
                            "skill_id = "+entity.getSkill_id()+", ", "developer_id"+entity.getDeveloper_id()));
                    preparedStatement.setString(3, "developer_id = "+entity.getDeveloper_id());
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Link link) {
        try(Connection connection = cm.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LINK)){
            String table = link.getTable();
            String customer_id = "customer_id = "+link.getCustomer_id();
            String company_id = "company_id = "+link.getCompany_id();
            String developer_id = "developer_id = "+link.getDeveloper_id();
            String skill_id = "skill_id = "+link.getSkill_id();
            String project_id = "project_id = "+link.getProject_id();
            switch (table){
                case "customer_companies":
                    preparedStatement.setString(1, table);
                    preparedStatement.setString(2, String.format(CUSTOMER_COMPANIES, customer_id,
                            company_id, project_id));
                    preparedStatement.executeUpdate();
                    break;
                case "developer_skills":
                    preparedStatement.setString(1, table);
                    preparedStatement.setString(2, String.format(DEVELOPER_SKILLS, skill_id, developer_id));
                    preparedStatement.executeUpdate();
                    break;
                case "project_developers":
                    preparedStatement.setString(1, table);
                    preparedStatement.setString(2, String.format(PROJECT_DEVELOPERS, project_id, developer_id));
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException e){}
    }
}
