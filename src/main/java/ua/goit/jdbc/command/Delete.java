package ua.goit.jdbc.command;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.dto.*;
import ua.goit.jdbc.service.*;
import ua.goit.jdbc.view.ConsoleManager;

import java.sql.SQLException;

public class Delete implements Command{
    private static final String DELETE_MENU = """
            Enter the number of necessary table 
            1 - developer
            2 - project
            3 - skill
            4 - company
            5 - customer
            6 - link tables
            
            To return to the home page, enter 'exit'
            """;
    private static final String SUCCESS = "Your request proceeded successfully \n";
    private static final String ERROR = "Please enter your data again \n";


    private final ConsoleManager consoleManager;
    private final DeveloperService developerService;
    private final SkillService skillService;
    private final ProjectService projectService;
    private final LinkService linkService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public Delete(ConsoleManager consoleManager, ConnectionManager connectionManager) {
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
        return "delete";
    }

    @Override
    public void process() {
        boolean running = true;
        while (running){
            consoleManager.write(DELETE_MENU);
            String obtainedData = consoleManager.read();
            switch (obtainedData){
                case "1" -> developerDelete();
                case "2" -> projectDelete();
                case "3" -> skillDelete();
                case "4" -> companyDelete();
                case "5" -> customerDelete();
                case "6" -> linkDelete();
                case "exit" -> running=false;
            }
        }
    }

    private void linkDelete() {
        LinkDTO linkDTO = new LinkDTO();
        consoleManager.write("""
                Choose a table from the list below
                1 - customer_companies
                2 - developer_skills
                3 - project_developers""");
        String obtainedData = consoleManager.read();
        String table;
        Integer customer_id;
        Integer company_id;
        Integer project_id;
        Integer skill_id;
        Integer developer_id;

        switch (obtainedData){
            case "1":
                table = "customers_companies";
                consoleManager.write("Enter the id of customer to be deleted \n" + customerService.getAll());
                customer_id = Integer.parseInt(consoleManager.read());
                consoleManager.write("Enter the id of company to be deleted \n" + companyService.getAll());
                company_id = Integer.parseInt(consoleManager.read());
                consoleManager.write("Enter the id of project to be deleted \n" + projectService.getAll());
                project_id = Integer.parseInt(consoleManager.read());
                linkDTO.setTable(table);
                linkDTO.setCustomer_id(customer_id);
                linkDTO.setCompany_id(company_id);
                linkDTO.setProject_id(project_id);
                try{
                    linkService.delete(linkDTO);
                    consoleManager.write(SUCCESS);
                } catch (SQLException | NumberFormatException throwables) {
                    consoleManager.write(ERROR);
                    consoleManager.write("Enter the id of customer to be deleted \n" + customerService.getAll());
                    customer_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of company to be deleted \n" + companyService.getAll());
                    company_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of project to be deleted \n" + projectService.getAll());
                    project_id = Integer.parseInt(consoleManager.read());
                    linkDTO.setTable(table);
                    linkDTO.setCustomer_id(customer_id);
                    linkDTO.setCompany_id(company_id);
                    linkDTO.setProject_id(project_id);
                    try {
                        linkService.delete(linkDTO);
                        consoleManager.write(SUCCESS);
                    } catch (SQLException e) {
                        consoleManager.write(ERROR);
                    }
                }
                break;
            case "2":
                table = "developer_skills";
                consoleManager.write("Enter the id of developer to be deleted \n" + customerService.getAll());
                developer_id = Integer.parseInt(consoleManager.read());
                consoleManager.write("Enter the id of skill to be deleted \n" + companyService.getAll());
                skill_id = Integer.parseInt(consoleManager.read());
                linkDTO.setTable(table);
                linkDTO.setDeveloper_id(developer_id);
                linkDTO.setSkill_id(skill_id);
                try{
                    linkService.delete(linkDTO);
                    consoleManager.write(SUCCESS);
                } catch (SQLException | NumberFormatException throwables) {
                    consoleManager.write(ERROR);
                    consoleManager.write("Enter the id of developer to be deleted \n" + customerService.getAll());
                    developer_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of skill to be deleted \n" + companyService.getAll());
                    skill_id = Integer.parseInt(consoleManager.read());
                    linkDTO.setTable(table);
                    linkDTO.setDeveloper_id(developer_id);
                    linkDTO.setSkill_id(skill_id);
                    try {
                        linkService.delete(linkDTO);
                        consoleManager.write(SUCCESS);
                    } catch (SQLException e) {
                        consoleManager.write(ERROR);
                    }
                }
                break;

            case "3":
                table = "project_developers";
                consoleManager.write("Enter the id of developer to be deleted \n" + customerService.getAll());
                developer_id = Integer.parseInt(consoleManager.read());
                consoleManager.write("Enter the id of project to be deleted \n" + companyService.getAll());
                project_id = Integer.parseInt(consoleManager.read());
                linkDTO.setTable(table);
                linkDTO.setDeveloper_id(developer_id);
                linkDTO.setProject_id(project_id);
                try{
                    linkService.delete(linkDTO);
                    consoleManager.write(SUCCESS);
                } catch (SQLException | NumberFormatException throwables) {
                    consoleManager.write(ERROR);
                    consoleManager.write("Enter the id of project to be deleted \n" + customerService.getAll());
                    project_id = Integer.parseInt(consoleManager.read());
                    consoleManager.write("Enter the id of developer to be deleted \n" + companyService.getAll());
                    developer_id = Integer.parseInt(consoleManager.read());
                    linkDTO.setTable(table);
                    linkDTO.setDeveloper_id(developer_id);
                    linkDTO.setProject_id(project_id);
                    try {
                        linkService.delete(linkDTO);
                        consoleManager.write(SUCCESS);
                    } catch (SQLException e) {
                        consoleManager.write(ERROR);
                    }
                }
                break;
        }
    }

    private void customerDelete() {
        CustomerDTO customerDTO = new CustomerDTO();
        consoleManager.write("""
                Enter the id of customer to be deleted""");
        Integer obtained_id;
        try {
            obtained_id = Integer.parseInt(consoleManager.read());
            customerDTO.setCustomer_id(obtained_id);
            customerService.delete(customerDTO);
            consoleManager.write(SUCCESS);
        } catch (SQLException | NumberFormatException throwables) {
            consoleManager.write(ERROR);
            obtained_id = Integer.parseInt(consoleManager.read());
            customerDTO.setCustomer_id(obtained_id);
            try {
                customerService.delete(customerDTO);
                consoleManager.write(SUCCESS);
            } catch (SQLException e) {
                consoleManager.write(ERROR);
            }
        }
    }


    private void companyDelete() {
        CompanyDTO companyDTO = new CompanyDTO();
        consoleManager.write("""
                Enter the id of company to be deleted""");
        Integer temp;
        try {
            temp = Integer.parseInt(consoleManager.read());
            companyDTO.setCompany_id(temp);
            companyService.delete(companyDTO);
            consoleManager.write(SUCCESS);
        } catch (SQLException | NumberFormatException throwables) {
            consoleManager.write(ERROR);
            temp = Integer.parseInt(consoleManager.read());
            companyDTO.setCompany_id(temp);
            try {
                companyService.delete(companyDTO);
                consoleManager.write(SUCCESS);
            } catch (SQLException e) {
                consoleManager.write(ERROR);
            }
        }
    }

    private void skillDelete() {
        SkillDTO skillDTO = new SkillDTO();
        consoleManager.write("""
                Enter the id of skill to be deleted""");
        Integer skill_id;
        try {
            skill_id = Integer.parseInt(consoleManager.read());
            skillDTO.setSkill_id(skill_id);
            skillService.delete(skillDTO);
            consoleManager.write(SUCCESS);
        } catch (SQLException | NumberFormatException throwables) {
            consoleManager.write(ERROR);
            skill_id = Integer.parseInt(consoleManager.read());
            skillDTO.setSkill_id(skill_id);
            try {
                skillService.delete(skillDTO);
                consoleManager.write(SUCCESS);
            } catch (SQLException e) {
                consoleManager.write(ERROR);
            }
        }
    }

    private void projectDelete() {
        ProjectDTO projectDTO = new ProjectDTO();
        consoleManager.write("""
                Enter the id of project to be deleted""");
        Integer project_id;
        try {
            project_id = Integer.parseInt(consoleManager.read());
            projectDTO.setProject_id(project_id);
            projectService.delete(projectDTO);
            consoleManager.write(SUCCESS);
        } catch (SQLException | NumberFormatException throwables) {
            consoleManager.write(ERROR);
            project_id = Integer.parseInt(consoleManager.read());
            projectDTO.setProject_id(project_id);
            try {
                projectService.delete(projectDTO);
                consoleManager.write(SUCCESS);
            } catch (SQLException e) {
                consoleManager.write(ERROR);
            }
        }
    }

    private void developerDelete() {

        DeveloperDTO developerDTO = new DeveloperDTO();
        consoleManager.write("""
                Enter the id of developer to be deleted""");

        Integer developer_id;
        try {
        developer_id = Integer.parseInt(consoleManager.read());
        developerDTO.setDeveloper_id(developer_id);
            developerService.delete(developerDTO);
            consoleManager.write(SUCCESS);
        } catch (SQLException | NumberFormatException throwables) {
            consoleManager.write(ERROR);
            developer_id = Integer.parseInt(consoleManager.read());
            developerDTO.setDeveloper_id(developer_id);
            try {
                developerService.delete(developerDTO);
                consoleManager.write(SUCCESS);
            } catch (SQLException e) {
                consoleManager.write(ERROR);
            }
        }
    }
}
