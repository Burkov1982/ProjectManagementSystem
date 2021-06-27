package ua.goit.jdbc.service;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.ProjectDAO;
import ua.goit.jdbc.dao.model.Project;
import ua.goit.jdbc.dto.ProjectDTO;
import ua.goit.jdbc.view.ViewMessages;

import java.util.LinkedList;

public class ProjectService implements Service<ProjectDTO>{
    private ConnectionManager cm;
    private ProjectDAO projectDAO;
    private ViewMessages viewMessages = new ViewMessages();

    public ProjectService(ConnectionManager connectionManager){
        this.cm = connectionManager;
        projectDAO = new ProjectDAO(this.cm);
    }

    @Override
    public void create(ProjectDTO projectDTO){
        Project project = Converter.toProject(projectDTO);
        projectDAO.create(project);
    }

    @Override
    public void update(ProjectDTO projectDTO){
        Project project = Converter.toProject(projectDTO);
        projectDAO.update(project);
    }

    @Override
    public String getById(int id){
        return projectDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListProjects(projectDAO.getAll());
    }

    @Override
    public void delete(int id){
        projectDAO.delete(id);
    }

    public LinkedList<Project> getAllInList(){
        return projectDAO.getAll();
    }
}
