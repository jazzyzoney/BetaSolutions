package org.example.betasolutions.employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


   @GetMapping("/project/employees")
    public String getAllEmployees(){
        return "employeepage";
   }

    @PostMapping("/project/employee/new")
    public String createNewEmployee(){
        return "redirect: /project/employee";
    }
    @PostMapping("/project/employee/add")
    public String addExistingEmployee(){
        return "redirect: /project/employee";
    }

    @PostMapping("/project/employee/edit")
    public String editEmployee(){
        return "redirect: /project/employee";
    }

    @PostMapping("/project/employee/delete")
    public String deleteEmployee(){
        return "redirect: /project/employee";
    }

}
