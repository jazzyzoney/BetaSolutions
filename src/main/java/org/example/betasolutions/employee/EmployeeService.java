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

    //read
    public List<Employee> getAllEmployeesForProject(int projectID){
        return employeeRepository.getAllEmployeesForProject(projectID);
    }

    //read
    public List<Employee> getAllEmployeesNotOnProject(int projectID){
        return employeeRepository.getAllEmployeesNotOnProject(projectID);
    }

    //add employee to project
    public void addExistingEmployeeToProject(int employeeID, int projectID){
        employeeRepository.addExistingEmployeeToProject(employeeID, projectID);
    }

    //add employee to task
    public void addExistingEmployeeToTask(int employeeID, int taskID, int projectID){
        employeeRepository.addExistingEmployeeToTask(employeeID, taskID, projectID);
    }

    //add employee to subtask
    public void addExistingEmployeeToSubTask(int employeeID, int taskID, int subTaskID, int projectID){
        employeeRepository.addExistingEmployeeToSubTask(employeeID, taskID, subTaskID, projectID);
    }

    //update - not in use yet, so no test for this method
    public void editEmployee(Employee employee){
        employeeRepository.editEmployee(employee);
    }

    //delete - not in use yet, so no test for this method
    public void deleteEmployee(int employeeID){
        employeeRepository.deleteEmployee(employeeID);
    }
}