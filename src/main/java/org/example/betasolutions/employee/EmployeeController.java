package org.example.betasolutions.employee;

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

   //@GetMapping("/project/employees") return "employeepage"
}
