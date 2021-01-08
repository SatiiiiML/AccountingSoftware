package com.company.service;

import com.company.mapper.AdminMapper;
import com.company.model.Admin;
import com.company.repository.AdminRepository;
import com.company.repository.AdminRepositoryImpl;
import com.company.util.Constants;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class AuthServiceImpl implements AuthService{
    Scanner scanner = new Scanner(System.in);
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    private AdminMapper adminMapper = new AdminMapper();

    @Override
    public void authenticate() {
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        if (Constants.USERNAME.equals(username) && Constants.PASSWORD.equals(password)){
            System.out.println("You are authenticated");
        } else {
            System.out.println("Wrong credentials. Please try again!");
            authenticate();
        }
    }

    @Override
    public void addUSer(String admin) {
        String adminString = String.valueOf(adminMapper.getUsersFromDatabaseCsv(admin));
        adminRepository.insetLine(adminString);
    }

    @Override
    public void changePassword(String admin) {
        String adminString = String.valueOf(adminMapper.getUsersFromDatabaseCsv(admin));
        adminRepository.updateLine(adminString);
    }

    @Override
    public void deleteUser(String string) {
        adminRepository.deleteLine(string);
    }
}
