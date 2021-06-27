package ua.goit.jdbc.service;

import ua.goit.jdbc.dao.model.*;
import ua.goit.jdbc.dto.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Converter {

    //Company part
    public static Company toCompany(CompanyDTO companyDTO){
        return new Company(companyDTO.getCompany_id(), companyDTO.getCompany_name(), companyDTO.getHeadquarters());
    }

    public static LinkedList<Company> toCompany(ResultSet resultSet){
        try{
            LinkedList<Company> companies = new LinkedList<>();
            while (resultSet.next()){
                Company company = new Company();
                company.setCompany_id(resultSet.getInt("company_id"));
                company.setCompany_name(resultSet.getString("company_name"));
                company.setHeadquarters(resultSet.getString("headquarters"));
                companies.addLast(company);
            }
            return companies;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public static CompanyDTO fromCompany(Company company){
        return new CompanyDTO(company.getCompany_id(), company.getCompany_name(), company.getHeadquarters());
    }

    //Customer part
    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name());
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name());
    }
    public static LinkedList<Customer> toCustomer(ResultSet resultSet){
        try{
            LinkedList<Customer> customers = new LinkedList<>();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setCustomer_name(resultSet.getString("customer_name"));
                customers.addLast(customer);
            }
            return customers;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
    //Developer part
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

    //Project part
    public static Project toProject(ProjectDTO projectDTO){
        return new Project(projectDTO.getProject_id(), projectDTO.getProject_name(), projectDTO.getProject_description(),
                projectDTO.getCost(), (Date) projectDTO.getStart_date());
    }

    public static LinkedList <Project> toProject (ResultSet resultSet){
        try{
            LinkedList<Project> projects  = new LinkedList<>();
            while (resultSet.next()){
                Project project = new Project();
                project.setProject_id(resultSet.getInt("project_id"));
                project.setProject_name(resultSet.getString("project_name"));
                project.setProject_description(resultSet.getString("project_description"));
                project.setCost(resultSet.getInt("cost"));
                project.setStart_date(resultSet.getDate("start_date"));
                projects.addLast(project);
            }
            return projects;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    //Skill part
    public static ProjectDTO fromProject(Project project){
        return new ProjectDTO(project.getProject_id(), project.getProject_name(), project.getProject_description(),
                project.getCost(), project.getStart_date());
    }
    public static Skill toSkill(SkillDTO skillDTO){
        return new Skill(skillDTO.getSkill_id(), skillDTO.getBranch(), skillDTO.getStage());
    }
    public static SkillDTO fromSkill (Skill skill){
        return new SkillDTO(skill.getSkill_id(), skill.getBranch(), skill.getStage());
    }
    public static LinkedList<Skill> toSkill (ResultSet resultSet) {
        try{
            LinkedList<Skill> skills  = new LinkedList<>();
            while (resultSet.next()){
                Skill skill = new Skill();
                skill.setSkill_id(resultSet.getInt("skill_id"));
                skill.setBranch(resultSet.getString("branch"));
                skill.setStage(resultSet.getString("stage"));
                skills.addLast(skill);
            }
            return skills;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

}



