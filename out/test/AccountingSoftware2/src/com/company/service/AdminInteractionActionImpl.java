package com.company.service;

import com.company.exception.InvalidUserInteractionException;
import com.company.model.*;

import java.util.Scanner;

public class AdminInteractionActionImpl implements AdminInteractionAction {
    Scanner scanner = new Scanner(System.in);

    @Override
    public Admin addAdminUser() {
        Admin admin = new Admin();
        admin.setUsername(getUsername());
        admin.setPassword(getPassword());
        admin.setDepartment(getDepartment());
        return admin;
    }

    @Override
    public Admin changePassword() {
        System.out.println("Under construction!");
        return addAdminUser();
    }


    private String getUsername() {
        System.out.println("Enter username: ");
        try {
            String username = scanner.nextLine();
            return username;
        } catch (Exception e) {
            System.out.println("You entered wrong username!");
        }
        return getUsername();
    }

    private String getPassword() {
        System.out.println("Enter password: ");
        try {
            String password = scanner.nextLine();
            return password;
        } catch (Exception e) {
            System.out.println("You entered wrong password");
        }
        return getPassword();
    }

    private Department getDepartment() {
        System.out.println("Enter department: " + Department.IT.getValue() + " - IT, " + Department.HR.getValue() + " - HR, " + Department.SALES.getValue() + " - SALES");
        try {
            Integer depId = Integer.parseInt(scanner.nextLine());
            if (depId != Department.IT.getValue() && depId != Department.HR.getValue() && depId != Department.SALES.getValue()) {
                throw new InvalidUserInteractionException();
            }
            Department department = depId == Department.IT.getValue() ? Department.IT : depId == Department.HR.getValue() ? Department.HR : Department.SALES;
            return department;
        } catch (Exception e) {
            System.out.println("Wrong department. Try again.");
        }
        return getDepartment();
    }
}
