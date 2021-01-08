package com.company.repository;

public interface AdminRepository {
    void insetLine(String csvLine);
    void deleteLine(String username);
    void updateLine(String password);
}
