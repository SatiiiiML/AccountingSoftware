package com.company.service;

import com.company.exception.InvalidUserInteractionException;
import com.company.model.Department;
import com.company.model.Employee;

import java.util.Scanner;

import static com.company.util.Constants.*;

public class UserInteractionServiceImpl implements UserInteractionService {
    private Scanner scanner = new Scanner(System.in);
    EmployeeInteractionService employeeInteractionService = new EmployeeInteractionActionImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
    AdminInteractionAction adminInteractionAction = new AdminInteractionActionImpl();

    @Override
    public void initInteraction() {
        switch (chooseInitialAction()) {
            case ACCESS_DATABASE:
                switch (chooseAccessDatabaseAction()) {
                    case LIST_EMPLOYEES:
                        employeeService.listEmployees();
                        break;
                    case ADD_EMPLOYEE:
                        Employee employee = employeeInteractionService.addEmployeeAction();
                        employeeService.addEmployee(employee);
                        break;
                    case DELETE_EMPLOYEE:
                        int idToBeDeleted = employeeInteractionService.deleteEmployeeAction();
                        employeeService.deleteEmployee(idToBeDeleted);
                        break;
                    case UPDATE_EMPLOYEE:
                        Employee employeeToBeUpdated = employeeInteractionService.updateEmployeeAction();
                        employeeService.updateEmployee(employeeToBeUpdated);
                        System.out.println("update employee");
                        break;
                }
                break;
            case VIEW_REPORTS:
                switch (chooseViewReportsAction()) {
                    case AVERAGE_SALARY_BY_COMPANY:
                        System.out.println("Show average salary" + employeeService.showAverageSalary());

                        break;
                    case AVERAGE_SALARY_BY_DEPARTMENT:
                        Department department = employeeInteractionService.chooseDepartmentAction();
                        Double avgByDepartment = employeeService.calculateAverageSalaryByDepartment(department);
                        System.out.println("Average salary for " + department + " is " + avgByDepartment);
                        break;
                    case AVERAGE_BY_SALARY_TOP10:
                        break;
                    case AVERAGE_YEARS_TOP10:
                        break;
                }
                break;
            case ADMIN_SETTINGS:
                switch (chooseAdminInteractionAction()){
                    case CHANGE_PASSWORD:
                        adminInteractionAction.changePassword();
                        break;
                    case ADD_USER:
                        adminInteractionAction.addAdminUser();
                        break;
                }
                break;
        }
        initInteraction();
    }

    private Integer chooseViewReportsAction() {
        System.out.println("Average salary by company press: press - " + AVERAGE_SALARY_BY_COMPANY);
        System.out.println("Average salary by company press: press - " + AVERAGE_SALARY_BY_DEPARTMENT);
        System.out.println("Top 10 most expensive employees in terms o salary: press - " + AVERAGE_BY_SALARY_TOP10);
        System.out.println("Top 10 most loyal employees: press - " + AVERAGE_YEARS_TOP10);

        try {
            Integer action = Integer.parseInt(scanner.nextLine());
            if (action != AVERAGE_SALARY_BY_COMPANY && action != AVERAGE_SALARY_BY_DEPARTMENT && action != AVERAGE_BY_SALARY_TOP10 && action != AVERAGE_YEARS_TOP10) {
                throw new InvalidUserInteractionException();
            }
            return action;
        } catch (Exception e) {
            System.out.println("Enter a valid number for action");
        }
        return chooseAccessDatabaseAction();
    }

    private Integer chooseInitialAction() {
        System.out.println("Choose action: ");
        System.out.println("Access Database - press " + ACCESS_DATABASE);
        System.out.println("View reports - press " + VIEW_REPORTS);
        System.out.println("Admin settings - press " + ADMIN_SETTINGS);
        try {
            Integer action = Integer.parseInt(scanner.nextLine());
            if (action != ACCESS_DATABASE && action != VIEW_REPORTS && action != ADMIN_SETTINGS) {
                throw new InvalidUserInteractionException();
            }
            return action;
        } catch (Exception ex) {
            System.out.println("Please enter a valid number: " + ACCESS_DATABASE + " (access database)" + " or " + VIEW_REPORTS + " (view reports) or " + ADMIN_SETTINGS + " (admin settings");
        }
        return chooseInitialAction();
    }

    private Integer chooseAccessDatabaseAction() {
        System.out.println("List employees press " + LIST_EMPLOYEES);
        System.out.println("Add employee press " + ADD_EMPLOYEE);
        System.out.println("Delete employee press " + DELETE_EMPLOYEE);
        System.out.println("Update employee press " + UPDATE_EMPLOYEE);

        try {
// putem creea RUN TIME EXCEPTION
            Integer action = Integer.parseInt(scanner.nextLine());
            if (action != LIST_EMPLOYEES && action != ADD_EMPLOYEE && action != DELETE_EMPLOYEE && action != UPDATE_EMPLOYEE) {
                throw new InvalidUserInteractionException();
            }
            return action;
        } catch (Exception e) {
            System.out.println("Please enter a valid number for your action!");
        }
        return chooseAccessDatabaseAction();
    }
    private Integer chooseAdminInteractionAction(){
        System.out.println("Change password: (press) - " + CHANGE_PASSWORD);
        System.out.println("Add user: (press) - " + ADD_USER);
        System.out.println("Delete user: (press) - " + DELETE_USER);
        try {
            Integer action = Integer.parseInt(scanner.nextLine());
            if (action != CHANGE_PASSWORD && action != ADD_USER && action != DELETE_USER){
                throw new InvalidUserInteractionException();
            }
            return action;
        }catch (Exception e){
            System.out.println("Please enter a valid action for Admin Settings!");
        }
        return chooseAdminInteractionAction();
    }
}
