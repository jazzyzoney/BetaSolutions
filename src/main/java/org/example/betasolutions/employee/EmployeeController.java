package org.example.betasolutions.employee;

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

   /*
   @GetMapping("/project/employees") return "employeepage"
    @PostMapping("/project/employee/new") return "redirect: /project/employee"
    @PostMapping("/project/employee/add") return "redirect: /project/employee"
    @PostMapping("/project/employee/update") return "redirect: /project/employee"
    @PostMapping("/project/employee/delete") return "redirect: /project/employee"
    */
}
