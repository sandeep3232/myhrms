package com.jpr.myhrms.db;

import com.jpr.myhrms.modal.Employee;

import java.util.List;

interface IDbHelper {

    int createEmployee(Employee employee);
    Employee getEmployee(String empId);
    void updateEmplyoee(Employee employee);
    int deleteEmplyoee(Employee employee);
    List<Employee> getAllEmplyoee();
    Employee getEmpByIDANDPassword(String empId,String empPassword);
    Employee getEmpByempLoginPin(int empLoginPin);
}
