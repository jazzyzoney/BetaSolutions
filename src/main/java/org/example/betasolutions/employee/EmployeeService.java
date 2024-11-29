package org.example.betasolutions.employee;

import java.util.List;

public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //create
    public int createEmployee(Employee employee){
        return employeeRepository.createNewEmployee(employee);
    }

    //read
    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    //update
    public void updateEmployee(Employee employee){
        employeeRepository.updateEmployee(employee);
    }

    //delete
    public void deleteEmployee(int employeeID){
        employeeRepository.deleteEmployee(employeeID);
    }
}
