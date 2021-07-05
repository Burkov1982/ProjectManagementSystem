package ua.goit.jdbc.view;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dao.model.*;
import ua.goit.jdbc.service.*;

import java.util.LinkedList;
import java.util.StringJoiner;

public class ViewMessages {
    private ProjectService projectService;
    private CompanyService companyService;
    private CustomerService customerService;
    private SkillService skillService;
    private DeveloperService developerService;
    private ConnectionManager connectionManager;

    public ViewMessages(){

    }

    public ViewMessages(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
        projectService = new ProjectService(this.connectionManager);
        companyService = new CompanyService(this.connectionManager);
        customerService = new CustomerService(this.connectionManager);
        skillService = new SkillService(this.connectionManager);
        developerService = new DeveloperService(this.connectionManager);
    }

    public String startMessage(){
        return "Приветствую в ProjectManagementSystem! \n";
    }

    public String sumOfSalariesOnProjectById(){
        StringBuilder message = new StringBuilder("Сумму зарплат разработчиков какого проекта Вы хотите получить?" +
                " (Введите идентификатор проекта) \n");
        LinkedList<Project> listOfProjects = projectService.getAllInList();
        for (Project project:listOfProjects){
            message.append(String.format("%d - %s \n", project.getProject_id(), project.getProject_name()));
        }
        return message.toString();
    }

    public String sumSelectQueryResult(Integer result){
        return String.format("Сумма зарплат всех разработчиков, выбраного Вами, проекта: %d", result);
    }

    public String errorMessage(){
        return "Введите корректные данные, пожалуйста.";
    }

    public String getDevelopersOfProjectById(){
        StringBuilder message = new StringBuilder("Список разработчиков какого проекта Вы хотите получить?" +
                " (Введите идентификатор проекта) \n");

        LinkedList<Project> listOfProjects = projectService.getAllInList();
        for (Project project:listOfProjects){
            message.append(String.format("%d - %s \n", project.getProject_id(), project.getProject_name()));
        }
        return message.toString();
    }

    public String joinListDevelopers(LinkedList<Developer> developers){
        StringJoiner joiner = new StringJoiner("\n");
        for (Developer developer:developers) {
            joiner.add(developer.toString());
        }
        return joiner.toString();
    }

    public String joinListProjects(LinkedList<Project> projects){
        StringJoiner joiner = new StringJoiner("\n");
        for (Project project:projects){
            joiner.add(project.toString());
        }
        return joiner.toString();
    }

    public String joinListSkills(LinkedList<Skill> skills){
        StringJoiner joiner = new StringJoiner("\n");
        for (Skill skill:skills){
            joiner.add(skill.toString());
        }
        return joiner.toString();
    }

    public String joinListCompanies(LinkedList<Company> companies){
        StringJoiner joiner = new StringJoiner("\n");
        for (Company company:companies){
            joiner.add(company.toString());
        }
        return joiner.toString();
    }

    public String joinListLinks(LinkedList<Link> links){
        StringJoiner joiner = new StringJoiner("\n");
        for (Link link:links){
            joiner.add(link.toString());
        }
        return joiner.toString();
    }

    public String joinListCustomers(LinkedList<Customer> customers){
        StringJoiner joiner = new StringJoiner("\n");
        for (Customer customer:customers){
            joiner.add(customer.toString());
        }
        return joiner.toString();
    }

    public String getDevelopersByLang(){
        StringBuilder message = new StringBuilder("Список разработчиков какой специализации Вы хотите получить? \n");
        LinkedList<Skill> skills = skillService.getAllInList();
        String temp = null;
        for (Skill skill:skills) {
            if (!skill.getBranch().equals(temp)){
                temp = skill.getBranch();
                message.append(String.format("- %s \n", skill.getBranch()));
            }
        }
        return message.toString();
    }

    public String getDevelopersByStage() {
        StringBuilder message = new StringBuilder("Список разработчиков какого уровня Вы хотите получить? \n");
        message.append(String.format("- %s \n", "Junior"));
        message.append(String.format("- %s \n", "Middle"));
        message.append(String.format("- %s \n", "Senior"));
        return message.toString();
    }

    public String getAllProjectModified(){
        LinkedList<Project> projects = projectService.getAllInList();

        StringBuilder message = new StringBuilder();
        for (Project project:projects){
            Integer amountOfDevsOnProject = developerService.getAmountOfDevOnProj(project.getProject_id());
            message.append(String.format("Дата создания: %s \n Название: %s \n Количество разработчиков на проекте: %s \n"
                    , project.getStart_date(), project.getProject_name(), amountOfDevsOnProject));
        }
        return message.toString();
    }

    public String successfulMessage() {
        return "Ваш запрос успешно обработан";
    }
}
