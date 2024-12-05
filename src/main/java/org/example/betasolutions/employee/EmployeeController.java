package org.example.betasolutions.employee; //henter hele employee package

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    //constructor
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //create
    @PostMapping("/project/employee/new")
    public String createNewEmployee(Employee employee) {
        employeeService.createNewEmployee(employee);
        return "redirect: /project/employees";
    }

    //read
    @GetMapping("/project/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("allEmployees", employees);
        return "employeepage";
    }

    //add employee to project
    @PostMapping("/project/employee/add/project") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToAssignment(Employee employee, int projectID) {
            employeeService.addExistingEmployeeToProject(employee, projectID);
            return "redirect: /project/employees";
        }

    //add employee to task
    @PostMapping("/project/employee/add/task") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToAssignment(Employee employee, int projectID, int taskID) {
        employeeService.addExistingEmployeeToTask(employee, taskID, projectID);
        return "redirect: /project/employees";
    }

    //add employee to subtask
    @PostMapping("/project/employee/add/subtask") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToAssignment(Employee employee, int projectID, int taskID, int subTaskID) {
        employeeService.addExistingEmployeeToSubTask(employee, taskID, subTaskID, projectID);
        return "redirect: /project/employees";
    }

        //update
        @PostMapping("/project/employee/edit")
        public String editEmployee (Employee employee){ //@RequestParam int employeeID ??
            employeeService.editEmployee(employee);
            return "redirect: /project/employees";
        }

        //delete
        @PostMapping("/project/employee/delete")
        public String deleteEmployee (int employeeID){
            employeeService.deleteEmployee(employeeID);
            return "redirect: /project/employees";
        }
}