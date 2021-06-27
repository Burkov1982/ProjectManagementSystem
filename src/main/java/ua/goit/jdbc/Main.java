package ua.goit.jdbc;

import ua.goit.jdbc.config.ConnectionManager;
import ua.goit.jdbc.service.*;
import ua.goit.jdbc.view.ViewMessages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ConnectionManager cm = new ConnectionManager("localhost", "", "postgres",
                "");
        ViewMessages view = new ViewMessages(cm);
        DeveloperService developerService = new DeveloperService(cm);
        CustomerService customerService = new CustomerService(cm);
        ProjectService projectService = new ProjectService(cm);
        CompanyService companyService = new CompanyService(cm);
        SkillService skillService = new SkillService(cm);
        System.out.println(view.startMessage());

        Scanner scanner = new Scanner(System.in);

        try {
            String strTemp;
            Integer intTemp;
            System.out.println(view.sumOfSalariesOnProjectById());
            intTemp = scanner.nextInt();
            System.out.println(developerService.getSumDevSalInProjById(intTemp));

            System.out.println(view.getDevelopersOfProjectById());
            intTemp = scanner.nextInt();
            System.out.println(developerService.getDevOfProjById(intTemp));

            System.out.println(view.getDevelopersByLang());
            strTemp = scanner.next();
            System.out.println(developerService.getDevByBranch(strTemp));

            System.out.println(view.getDevelopersByStage());
            strTemp = scanner.next();
            System.out.println(developerService.getDevByStage(strTemp));

            System.out.println(view.getAllProjectModified());
        } catch (InputMismatchException ex){
            System.out.println(view.errorMessage());
        }
    }
}
