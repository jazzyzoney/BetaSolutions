package org.example.betasolutions.project;

import org.example.betasolutions.employee.Employee;
import org.example.betasolutions.subProject.SubProject;
import org.example.betasolutions.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private List<SubProject> subProjectList = new ArrayList<>();
    private List <Task> taskList = new ArrayList<>();
    private List <Employee> employeeList = new ArrayList<>();

    public List<SubProject> getSubProjectList(){
        return subProjectList;
    }

    public List<Task> getTaskList(){
        return taskList;
    }

    public List<Employee> getEmployeeList(){
        return employeeList;
    }
}
