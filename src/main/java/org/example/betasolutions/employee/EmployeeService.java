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

    //add employee to project
    public void addExistingEmployeeToProject(Employee employee, int projectID){
        employeeRepository.addExistingEmployeeToProject(employee, projectID);
    }

    //add employee to task
    public void addExistingEmployeeToTask(Employee employee, int taskID, int projectID){
        employeeRepository.addExistingEmployeeToTask(employee, taskID, projectID);
    }

    //add employee to subtask
    public void addExistingEmployeeToSubTask(Employee employee, int taskID, int subTaskID, int projectID){
        employeeRepository.addExistingEmployeeToSubTask(employee, taskID, subTaskID, projectID);
    }

    //update
    public void editEmployee(Employee employee){
        employeeRepository.editEmployee(employee);
    }

    //delete
    public void deleteEmployee(int employeeID){
        employeeRepository.deleteEmployee(employeeID);
    }
}