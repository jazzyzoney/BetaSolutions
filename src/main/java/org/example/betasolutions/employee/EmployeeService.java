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
    public boolean createNewEmployee(Employee employee){
        return employeeRepository.createNewEmployee(employee);
    }

    //read
    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }

    public List<Employee> getAllEmployeesForProject(int projectID){
        return employeeRepository.getAllEmployeesForProject(projectID);
    }

    public List<Employee> getAllEmployeesNotOnProject(int projectID){
        return employeeRepository.getAllEmployeesNotOnProject(projectID);
    }
    public List<Employee> getAllEmployeesForTask(int projectID, int taskID){
        return employeeRepository.getAllEmployeesForTask(projectID, taskID);
    }
    public List<Employee> getAllemployeesNotAssingedToTaskForProject(int projectID){
        return employeeRepository.getAllemployeesNotAssingedToTaskForProject(projectID);
    }

    public List<Employee> getAllEmployeesForSubTask(int projectID, int taskID, int subTaskID){
        return employeeRepository.getAllEmployeesForSubTask(projectID, taskID, subTaskID);
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

    //update
    public void editEmployee(Employee employee){
        employeeRepository.editEmployee(employee);
    }

    //delete
    public void deleteEmployee(int employeeID){
        employeeRepository.deleteEmployee(employeeID);
    }
}