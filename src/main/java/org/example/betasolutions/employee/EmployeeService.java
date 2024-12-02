package org.example.betasolutions.employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    //constructor
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //create
    public int createNewEmployee(Employee employee){
        return employeeRepository.createNewEmployee(employee);
    }

    //read
    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    //update
    public void editEmployee(Employee employee){
        employeeRepository.updateEmployee(employee);
    }

    //delete
    public void deleteEmployee(int employeeID){
        employeeRepository.deleteEmployee(employeeID);
    }
}