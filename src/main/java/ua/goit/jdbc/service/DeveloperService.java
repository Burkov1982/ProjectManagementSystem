package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.DeveloperDAO;
import ua.goit.jdbc.dao.model.Developer;
import ua.goit.jdbc.dto.DeveloperDTO;
import ua.goit.jdbc.view.ViewMessages;

import java.util.LinkedList;

public class DeveloperService implements Service<DeveloperDTO>{
    private ConnectionManager connectionManager;
    private DeveloperDAO developerDAO;
    private ViewMessages viewMessages = new ViewMessages();

    public DeveloperService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        developerDAO = new DeveloperDAO(this.connectionManager);
    }

    @Override
    public void create(DeveloperDTO developerDTO){
        Developer developer = Converter.toDeveloper(developerDTO);
        developerDAO.create(developer);
    }

    @Override
    public void update(DeveloperDTO developerDTO){
        Developer developer = Converter.toDeveloper(developerDTO);
        developerDAO.update(developer);
    }

    @Override
    public void delete(int id){
        developerDAO.delete(id);
    }

    @Override
    public String getById(int id){
        return developerDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListDevelopers(developerDAO.getAll());
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

    public String getSumDevSalInProjById(Integer project_id) {
        if (project_id != null){
            Integer sum = developerDAO.getSumOfDevSelByProjId(project_id);
            return viewMessages.sumSelectQueryResult(sum);
        } else {
            return viewMessages.errorMessage();
        }
    }

    public String getDevByBranch(String branch) {
        if (branch != null){
            LinkedList<Developer> developers = developerDAO.getDevelopersByBranch(branch);
            return viewMessages.joinListDevelopers(developers);
        } else {
            return viewMessages.errorMessage();
        }
    }

    public String getDevByStage(String stage) {
        if (stage != null){
            LinkedList<Developer> developers = developerDAO.getDevelopersByStage(stage);
            return viewMessages.joinListDevelopers(developers);
        } else {
            return viewMessages.errorMessage();
        }
    }
}
