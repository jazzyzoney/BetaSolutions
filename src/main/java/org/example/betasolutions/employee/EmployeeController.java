package org.example.betasolutions.employee; //henter hele employee package

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    //constructor
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //create
    @PostMapping("/project/employee/new")
    public String createNewEmployee(Employee employee){
        employeeService.createNewEmployee(employee);
        return "redirect: /project/employee";
    }

    //read
    @GetMapping("/project/employees")
    public String getAllEmployees(Model model){
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("allEmployees", employees);
        return "employeepage";
   }

    //in progress
    @PostMapping("/project/employee/add") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToAssignment(Employee employee, String assignment, String idName){
        employeeService.addExistingEmployeeToAssignment(employee, assignment, idName);
        return "redirect: /project/employee";
    }

    //update
    @PostMapping("/project/employee/edit")
    public String editEmployee(Employee employee){ //@RequestParam int employeeID ??
        employeeService.editEmployee(employee);
        return "redirect: /project/employee";
    }

    //delete
    @PostMapping("/project/employee/delete")
    public String deleteEmployee(int employeeID){
        employeeService.deleteEmployee(employeeID);
        return "redirect: /project/employee";
    }
}