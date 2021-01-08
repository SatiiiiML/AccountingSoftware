package com.company.mapper;

import com.company.model.Admin;
import com.company.model.Department;

import static com.company.util.Constants.COMMA_DELIMITER;

public class AdminMapper {
    public Admin getUsersFromDatabaseCsv(String csvLine) {
        if (csvLine != null && csvLine.length() != 0) {
            String[] values = csvLine.split(COMMA_DELIMITER);
            Admin admin = new Admin();
            admin.setUsername(values[0]);
            admin.setPassword(values[1]);
            if (values[2].equals("IT")) {
                admin.setDepartment(Department.IT);
            } else if (values[2].equals("HR")){
                admin.setDepartment(Department.HR);
            } else {
                admin.setDepartment(Department.SALES);
            }
        } else {
        }
        return new Admin();
    }
}
