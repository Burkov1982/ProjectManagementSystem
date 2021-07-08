package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.DeveloperDAO;
import ua.goit.jdbc.dao.model.Developer;
import ua.goit.jdbc.dto.DeveloperDTO;
import ua.goit.jdbc.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DeveloperService implements Service<DeveloperDTO>{
    private final DeveloperDAO developerDAO;
    private final ViewMessages viewMessages = new ViewMessages();

    public DeveloperService(ConnectionManager connectionManager) {
        developerDAO = new DeveloperDAO(connectionManager);
    }

    @Override
    public void create(DeveloperDTO developerDTO){
        Developer developer = toDeveloper(developerDTO);
        developerDAO.create(developer);
    }

    @Override
    public void update(DeveloperDTO developerDTO) throws SQLException {
        Developer developer = toDeveloper(developerDTO);
        developerDAO.update(developer);
    }

    @Override
    public void delete(DeveloperDTO developerDTO) throws SQLException {
        developerDAO.delete(toDeveloper(developerDTO));
    }

    @Override
    public String getById(int id) throws SQLException {
        return developerDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListDevelopers(developerDAO.getAll());
    }

    @Override
    public String getAll(DeveloperDTO entity) {
        return null;
    }

    public static Developer toDeveloper(DeveloperDTO developerDTO){
        return new Developer(developerDTO.getDeveloper_id(), developerDTO.getFirst_name(), developerDTO.getLast_name(),
                developerDTO.getGender(), developerDTO.getSalary());
    }

    public static DeveloperDTO fromDeveloper(Developer developer){
        return new DeveloperDTO(developer.getDeveloper_id(), developer.getFirst_name(), developer.getLast_name(),
                developer.getGender(), developer.getSalary());
    }

    public static LinkedList <Developer> toDeveloper (ResultSet resultSet){
        try{
            LinkedList<Developer> developers = new LinkedList<>();
            while (resultSet.next()){
                Developer developer = new Developer();
                developer.setDeveloper_id(resultSet.getInt("developer_id"));
                developer.setFirst_name(resultSet.getString("first_name"));
                developer.setLast_name(resultSet.getString("last_name"));
                developer.setGender(resultSet.getString("gender"));
                developer.setSalary(resultSet.getInt("salary"));
                developers.addLast(developer);
            }
            return developers;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public int getAmountOfDevOnProj(Integer project_id){
        int result = 0;
        if (project_id!=null){
            result = developerDAO.getDevelopersOfProjectById(project_id).size();
        }
        return result;
    }

    public String getDevOfProjById(Integer project_id){
        if (project_id != null){
            return viewMessages.joinListDevelopers(developerDAO.getDevelopersOfProjectById(project_id));
        } else {
            return null;
        }
    }

    public String getSumDevSalInProjById(Integer project_id) throws SQLException {
        if (project_id != null){
            Integer sum = developerDAO.getSumOfDevSelByProjId(project_id);
            return viewMessages.sumSelectQueryResult(sum);
        }
        return null;
    }

    public String getDevByBranch(String branch) throws SQLException {
        if (branch != null){
            LinkedList<Developer> developers = developerDAO.getDevelopersByBranch(branch);
            return viewMessages.joinListDevelopers(developers);
        }
        return null;
    }

    public String getDevByStage(String stage) throws SQLException {
        if (stage != null){
            LinkedList<Developer> developers = developerDAO.getDevelopersByStage(stage);
            return viewMessages.joinListDevelopers(developers);
        }
        return null;
    }
}
