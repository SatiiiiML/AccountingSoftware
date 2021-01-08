package com.company.service;

import com.company.model.Department;
import com.company.model.Employee;

public interface EmployeeService {
    void listEmployees();
    void addEmployee(Employee employee);
    void deleteEmployee(Integer id);
    void updateEmployee(Employee employee);
    Double showAverageSalary();
    Double calculateAverageSalaryByDepartment(Department department);
    void listTop10Salary(Employee employee, Double salary);
}
