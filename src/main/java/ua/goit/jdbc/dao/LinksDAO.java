package ua.goit.jdbc.dao;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.view.ViewMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LinksDAO{
    private static final String INSERT_CUSTOMER_COMPANIES_LINK = "INSERT INTO customer_companies (customer_id, company_id, " +
            "project_id) VALUES (?, ?, ?);";
    private static final String INSERT_PROJECT_DEVELOPERS_LINK = "INSERT INTO project_developers (project_id, developer_id) " +
            "VALUES (?, ?)";
    private static final String INSERT_DEVELOPER_SKILLS_LINK = "INSERT INTO developer_skills (skill_id, developer_id)" +
            "VALUES (?, ?)";


    private final ConnectionManager cm;
    private final ViewMessages viewMessages = new ViewMessages();
    public LinksDAO(ConnectionManager connectionManager) {
        cm = connectionManager;
    }

    public String create(String table, Integer project_id, Integer customer_id, Integer developer_id, Integer company_id
            , Integer skill_id) {
        try (Connection connection = cm.getConnection()){

            switch (table) {
                case ("customers_companies"):
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_COMPANIES_LINK)) {
                        if (!(customer_id == null && company_id == null && project_id == null)) {
                            preparedStatement.setInt(1, customer_id);
                            preparedStatement.setInt(2, company_id);
                            preparedStatement.setInt(3, project_id);
                            preparedStatement.executeUpdate();
                            return viewMessages.successfulMessage();
                        }
                    }
                    break;

                case ("project_developers"):
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_DEVELOPERS_LINK)) {
                        if (!(project_id == null && developer_id == null)){
                            preparedStatement.setInt(1, project_id);
                            preparedStatement.setInt(2, developer_id);
                            preparedStatement.executeUpdate();
                            return viewMessages.successfulMessage();
                        }
                    }
                    break;

                case ("developer_skills"):
                    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEVELOPER_SKILLS_LINK)){
                        if (!(skill_id == null && developer_id == null)){
                            preparedStatement.setInt(1, skill_id);
                            preparedStatement.setInt(2, developer_id);
                            preparedStatement.executeUpdate();
                            return viewMessages.successfulMessage();
                        }
                    }
                    break;

                default:
                    return viewMessages.errorMessage();
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return viewMessages.errorMessage();
    }
}
