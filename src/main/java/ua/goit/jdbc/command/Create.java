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

public class Create implements Command{
    private final DeveloperService developerService;
    private final SkillService skillService;
    private final ProjectService projectService;
    private final LinkService linkService;
    private final CompanyService companyService;
    private final CustomerService customerService;
    private static final String CREATE_MENU = """
            Enter the number of necessary table 
            1 - developer
            2 - project
            3 - skill
            4 - company
            5 - customer
            6 - link tables
            
            To return to the home page, enter 'exit'
            """;

    private final ConsoleManager consoleManager;

    public Create(ConsoleManager consoleManager, ConnectionManager cm) {
        this.consoleManager = consoleManager;
        developerService = new DeveloperService(cm);
        skillService = new SkillService(cm);
        projectService = new ProjectService(cm);
        linkService = new LinkService(cm);
        companyService = new CompanyService(cm);
        customerService = new CustomerService(cm);
    }

    @Override
    public String commandName() {
        return "create";
    }

    @Override
    public void process() {
        boolean running = true;
        while (running){
            consoleManager.write(CREATE_MENU);
            String obtainedData = consoleManager.read();
            switch (obtainedData){
                case "1" -> developerCreate();
                case "2" -> projectCreate();
                case "3" -> skillCreate();
                case "4" -> companyCreate();
                case "5" -> customerCreate();
                case "6" -> linkCreate();
                case "exit" -> running=false;
            }
        }
    }

    private void linkCreate() {
            LinkDTO linkDTO = new LinkDTO();
            Integer project_id;
            Integer developer_id;
            Integer skill_id;
            Integer company_id;
            Integer customer_id;
            try {
            consoleManager.write("""
                    Enter the number of necessary table 
                    1 - customer_companies
                    2 - developer_skills
                    3 - project_developers
                    """);
            String table;
            String obtainedData = consoleManager.read();
            switch (obtainedData) {
                case "1":
                    table = "customer_companies";
                    consoleManager.write("Enter the id of customer \n" + customerService.getAll());
                    customer_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of company \n" + companyService.getAll());
                    company_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of project \n" + projectService.getAll());
                    project_id = Integer.parseInt(consoleManager.read());
                    linkDTO.setTable(table);
                    linkDTO.setCustomer_id(customer_id);
                    linkDTO.setCompany_id(company_id);
                    linkDTO.setProject_id(project_id);
                    linkService.create(linkDTO);
                    consoleManager.write("Your request is proceeded successfully\n");
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
                    linkService.create(linkDTO);
                    consoleManager.write("Your request id proceeded successfully \n");
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
                    linkService.create(linkDTO);
                    consoleManager.write("Your request proceeded successfully\n");
                    break;
            }
        } catch (SQLException e){
            consoleManager.write("Please enter your data again");
        }
    }

    private void customerCreate() {
        try {
        CustomerDTO customerDTO = new CustomerDTO();
        consoleManager.write("Enter the name of customer");
        String name = consoleManager.read();
        customerDTO.setCustomer_name(name);
        customerService.create(customerDTO);
        consoleManager.write("Your request proceeded successfully \n");
        } catch (SQLException e){
            consoleManager.write("Please enter your data again");
        }
    }

    private void companyCreate() {
        try{
            CompanyDTO companyDTO = new CompanyDTO();
            consoleManager.write("Enter the name of company");
            String name = consoleManager.read();
            consoleManager.write("Enter the headquarter of company");
            String headquarters = consoleManager.read();
            companyDTO.setCompany_name(name);
            companyDTO.setHeadquarters(headquarters);
            companyService.create(companyDTO);
            consoleManager.write("Your request proceeded successfully \n");
        } catch (SQLException e){
            consoleManager.write("Please enter your data again");
        }
    }

    private void skillCreate() {
        try {
        SkillDTO skillDTO = new SkillDTO();
        consoleManager.write("Enter the type of code");
        String branch = consoleManager.read();
        consoleManager.write("""
                Choose the skill level
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
        skillDTO.setBranch(branch);
        skillDTO.setStage(stage);
        skillService.create(skillDTO);
        consoleManager.write("Your request proceeded successfully \n");
        } catch (SQLException e){
            consoleManager.write("Please enter your data again");
        }
    }

    private void projectCreate() {
        try {
        ProjectDTO projectDTO = new ProjectDTO();
        consoleManager.write("Enter the name of project");
        String name = consoleManager.read();
        consoleManager.write("Enter the description of project");
        String description = consoleManager.read();
        consoleManager.write("Enter the cost of project (only numbers)");
        Integer cost;
        try {
            cost = Integer.parseInt(consoleManager.read());
        } catch (NumberFormatException exception){
            consoleManager.write("Please enter your data again");
            cost = Integer.parseInt(consoleManager.read());
        }
        consoleManager.write("Enter the start date of project (dd-mm-yyyy)");
        List<String> list = Arrays.asList(consoleManager.read().split("-"));
        LocalDate localDate = LocalDate.of(Integer.parseInt(list.get(list.size()-1)),
                Integer.parseInt(list.get(list.size()-3)), Integer.parseInt(list.get(0)));
        Date date = Date.valueOf(localDate);

        projectDTO.setProject_name(name);
        projectDTO.setProject_description(description);
        projectDTO.setStart_date(date);
        projectDTO.setCost(cost);
        projectService.create(projectDTO);
        consoleManager.write("Your request proceeded successfully \n");
        } catch (SQLException e){
            consoleManager.write("Please enter your data again");
        }
    }

    private void developerCreate() {
        DeveloperDTO developerDTO = new DeveloperDTO();
        try {
            consoleManager.write("Enter the firstname of developer");
            String first_name = consoleManager.read();
            consoleManager.write("Enter the lastname of developer");
            String last_name = consoleManager.read();
            consoleManager.write("Choose gender of developer \n 1 - male \n 2 - female");
            String gender = null;
            String obtainedData = consoleManager.read();
            switch (obtainedData) {
                case "1" -> gender = "male";
                case "2" -> gender = "female";
                default -> gender = "male";
            }
            consoleManager.write("Enter the salary of developer (only numbers)");
            Integer salary = Integer.parseInt(consoleManager.read());
            developerDTO.setFirst_name(first_name);
            developerDTO.setLast_name(last_name);
            developerDTO.setGender(gender);
            developerDTO.setSalary(salary);
            developerService.create(developerDTO);
            consoleManager.write("Your request proceeded successfully \n");
        } catch (NumberFormatException exception){
            consoleManager.write("Please enter your data again");
        }
    }
}
