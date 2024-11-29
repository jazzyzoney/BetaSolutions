package org.example.betasolutions.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.BetaSolutions.employee.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

   @GetMapping("/project/employees")
    public String getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();

        return "employeepage";
   }

    @PostMapping("/project/employee/new")
    public String createNewEmployee(){
        return "redirect: /project/employee";
    }

    @PostMapping("/project/employee/add") //add employee to project/subproject/task/subtask
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