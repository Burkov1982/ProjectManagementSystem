package ua.goit.jdbc.command;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dto.*;
import ua.goit.jdbc.service.*;
import ua.goit.jdbc.view.ConsoleManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Update implements Command{
    private final DeveloperService developerService;
    private final SkillService skillService;
    private final ProjectService projectService;
    private final LinkService linkService;
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final ConsoleManager consoleManager;

    private static final String UPDATE_MENU = """
    Enter the number of the necessary table
    1 - developer
    2 - project
    3 - skill
    4 - company
    5 - customer
    6 - link tables
            
    To return to the home page, enter 'exit'""";
    private static final String SUCCESS = "Your request proceeded successfully \n";
    private static final String ERROR = "Please enter your data again \n";


    public Update(ConsoleManager consoleManager, ConnectionManager connectionManager) {
        this.consoleManager = consoleManager;
        developerService = new DeveloperService(connectionManager);
        skillService = new SkillService(connectionManager);
        projectService = new ProjectService(connectionManager);
        linkService = new LinkService(connectionManager);
        companyService = new CompanyService(connectionManager);
        customerService = new CustomerService(connectionManager);
    }

    @Override
    public String commandName() {
        return "update";
    }

    @Override
    public void process() {
        boolean running = true;
        while (running){
            consoleManager.write(UPDATE_MENU);
            String obtainedData = consoleManager.read();
            switch (obtainedData){
                case "1" -> developerUpdate();
                case "2" -> projectUpdate();
                case "3" -> skillUpdate();
                case "4" -> companyUpdate();
                case "5" -> customerUpdate();
                case "6" -> linkUpdate();
                case "exit" -> running=false;
            }
        }
    }

    private void linkUpdate() {
        LinkDTO linkDTO = new LinkDTO();
        LinkDTO newLink = new LinkDTO();
        Integer project_id;
        Integer developer_id;
        Integer skill_id;
        Integer company_id;
        Integer customer_id;
        try {
            consoleManager.write("""
                   Choose the table to update
                    1 - customer_companies
                    2 - developer_skills
                    3 - project_developers
                    """);
            String table;
            String obtainedData = consoleManager.read();
            switch (obtainedData) {
                case "1":
                    table = "customers_companies";
                    consoleManager.write("Enter the id of customer\n" + customerService.getAll());
                    customer_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of company \n" + companyService.getAll());
                    company_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of project \n" + projectService.getAll());
                    project_id = Integer.parseInt(consoleManager.read());
                    linkDTO.setTable(table);
                    linkDTO.setCustomer_id(customer_id);
                    linkDTO.setCompany_id(company_id);
                    linkDTO.setProject_id(project_id);

                    consoleManager.write("Enter a new id of customer\n" + customerService.getAll());
                    customer_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter a new id of company \n" + companyService.getAll());
                    company_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter a new id of project \n" + projectService.getAll());
                    project_id = Integer.parseInt(consoleManager.read());
                    newLink.setTable(table);
                    newLink.setCustomer_id(customer_id);
                    newLink.setCompany_id(company_id);
                    newLink.setProject_id(project_id);

                    linkService.update(newLink, linkDTO);

                    consoleManager.write(SUCCESS);
                    break;
                case "2":
                    table = "developer_skills";
                    consoleManager.write("Enter the id of skill \n" + skillService.getAll());
                    skill_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of developer \n" + developerService.getAll());
                    developer_id = Integer.parseInt(consoleManager.read());
                    linkDTO.setTable(table);
                    linkDTO.setSkill_id(skill_id);
                    linkDTO.setDeveloper_id(developer_id);

                    consoleManager.write("Enter a new id of skill \n" + skillService.getAll());
                    skill_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter a new id of developer \n" + developerService.getAll());
                    developer_id = Integer.parseInt(consoleManager.read());
                    newLink.setTable(table);
                    newLink.setSkill_id(skill_id);
                    newLink.setDeveloper_id(developer_id);
                    linkService.update(newLink, linkDTO);

                    consoleManager.write(SUCCESS);
                    break;
                case "3":
                    table = "project_developers";
                    consoleManager.write("Enter the id of project \n" + projectService.getAll());
                    project_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of developer \n" + developerService.getAll());
                    developer_id = Integer.parseInt(consoleManager.read());
                    linkDTO.setDeveloper_id(developer_id);
                    linkDTO.setTable(table);
                    linkDTO.setProject_id(project_id);


                    consoleManager.write("Enter a new id of project \n" + projectService.getAll());
                    project_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter a new id of developer \n" + developerService.getAll());
                    developer_id = Integer.parseInt(consoleManager.read());
                    newLink.setDeveloper_id(developer_id);
                    newLink.setTable(table);
                    newLink.setProject_id(project_id);

                    linkService.update(newLink, linkDTO);
                    consoleManager.write(SUCCESS);
                    break;
            }
        } catch (SQLException e){
            consoleManager.write(ERROR);
        }
    }

    private void customerUpdate() {
        try{
            CustomerDTO customerDTO = new CustomerDTO();
            consoleManager.write("Enter the id of customer");
            Integer integer = Integer.parseInt(consoleManager.read());
            consoleManager.write("Enter a new name of customer \n");
            String name = consoleManager.read();
            customerDTO.setCustomer_id(integer);
            customerDTO.setCustomer_name(name);

            customerService.update(customerDTO);
            consoleManager.write(SUCCESS);
        }catch (SQLException e){
            consoleManager.write(ERROR);
        }
    }

    private void companyUpdate() {
        try{
            CompanyDTO companyDTO = new CompanyDTO();
            consoleManager.write("Enter the id of project");
            Integer integer = Integer.parseInt(consoleManager.read());
            consoleManager.write("Enter a new name of company \n");
            String name = consoleManager.read();
            consoleManager.write("Enter a new headquarter of company \n");
            String headquarters = consoleManager.read();
            companyDTO.setCompany_id(integer);
            companyDTO.setCompany_name(name);
            companyDTO.setHeadquarters(headquarters);
            companyService.update(companyDTO);
            consoleManager.write(SUCCESS);
        } catch (SQLException | NumberFormatException e){
            consoleManager.write(ERROR);
        }
    }

    private void skillUpdate() {
        try {
            SkillDTO skillDTO = new SkillDTO();
            consoleManager.write("Enter the id of skill");
            Integer skill = Integer.parseInt(consoleManager.read());
            consoleManager.write("Enter a new branch\n");
            String branch = consoleManager.read();
            consoleManager.write("""
                Enter a new skill level
                1 - Junior
                2 - Middle
                3 - Senior
                """);
            String stage = null;
            String obtainedData = consoleManager.read();
            switch (obtainedData){
                case "1" -> stage = "Junior";
                case "2" -> stage = "Middle";
                case "3" -> stage = "Senior";
                default -> stage = "Junior";
            }
            skillDTO.setSkill_id(skill);
            skillDTO.setBranch(branch);
            skillDTO.setStage(stage);
            skillService.update(skillDTO);
            consoleManager.write(SUCCESS);
        } catch (SQLException | NumberFormatException e){
            consoleManager.write(ERROR);
        }
    }

    private void projectUpdate() {
        ProjectDTO projectDTO = new ProjectDTO();
        try {
        consoleManager.write("Enter the id of project");
        Integer integer = Integer.parseInt(consoleManager.read());
        consoleManager.write("Enter a new name of project\n");
        String name = consoleManager.read();
        consoleManager.write("Enter a new description of project\n");
        String description = consoleManager.read();
        consoleManager.write("Enter a new cost of project\n");
        Integer cost;
        try {
            cost = Integer.parseInt(consoleManager.read());
        } catch (NumberFormatException exception){
            consoleManager.write(ERROR);
            cost = Integer.parseInt(consoleManager.read());
        }
        consoleManager.write("Enter a new start date of project (dd-mm-yyyy)");
        List<String> list = Arrays.asList(consoleManager.read().split("-"));
        LocalDate localDate = LocalDate.of(Integer.parseInt(list.get(list.size()-1)),
                Integer.parseInt(list.get(list.size()-3)), Integer.parseInt(list.get(0)));
        Date date = Date.valueOf(localDate);
        projectDTO.setProject_id(integer);
        projectDTO.setProject_name(name);
        projectDTO.setProject_description(description);
        projectDTO.setStart_date(date);
        projectDTO.setCost(cost);
        projectService.update(projectDTO);
        consoleManager.write(SUCCESS);
        } catch (SQLException | NumberFormatException e){
            consoleManager.write(ERROR);
        }
    }

    private void developerUpdate() {
        DeveloperDTO developerDTO = new DeveloperDTO();
        try {
            consoleManager.write("Enter the id of developer");
        Integer integer = Integer.parseInt(consoleManager.read());
        consoleManager.write("Enter a new firstname of developer");
        String first_name = consoleManager.read();
        consoleManager.write("Enter a new lastname of developer");
        String last_name = consoleManager.read();
        consoleManager.write("Choose the gender of developer \n 1 - male \n 2 - female");
        String gender = null;
        String obtainedData = consoleManager.read();
        switch (obtainedData){
            case "1" -> gender = "male";
            case "2" -> gender = "female";
            default -> gender = "male";
        }
        consoleManager.write("Enter a new salary of developer");
        Integer salary = Integer.parseInt(consoleManager.read());
        developerDTO.setDeveloper_id(integer);
        developerDTO.setFirst_name(first_name);
        developerDTO.setLast_name(last_name);
        developerDTO.setGender(gender);
        developerDTO.setSalary(salary);
        developerService.update(developerDTO);
        consoleManager.write(SUCCESS);

        } catch (SQLException | NumberFormatException throwables){
            consoleManager.write(ERROR);
        }
    }
}
