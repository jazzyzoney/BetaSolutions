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

    //add employee to assignment
    public void addExistingEmployeeToAssignment(Employee employee, String assignment, String idName){
        employeeRepository.addExistingEmployeeToAssignment(employee, assignment, idName);
    }

    //update
    public void editEmployee(Employee employee){
        employeeRepository.editEmployee(employee);
    }

    //delete
    public void deleteEmployee(int employeeID){
        employeeRepository.deleteEmployee(employeeID);
    }
    public List<String> GetAllEmployeeOffices(){
        return employeeRepository.GetAllEmployeeOffices();
    }
}