package com.company.service;

import com.company.model.Admin;

public interface AuthService {
    void authenticate();
    void addUSer(String admin);
    void deleteUser(String string);
    void changePassword(String admin);
}
