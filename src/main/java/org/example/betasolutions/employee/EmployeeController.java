package org.example.betasolutions.employee; //henter hele employee package

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final HttpSession session;

    //constructor
    public EmployeeController(EmployeeService employeeService, HttpSession session) {
        this.employeeService = employeeService;
        this.session = session;
    }

    //create
    @PostMapping("/project/employee/new")
    public String createNewEmployee(Employee employee) {
        employeeService.createNewEmployee(employee);
        return "redirect:/project/employees";
    }

    //read
    @GetMapping("/project/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println(employees.size());
        model.addAttribute("allEmployees", employees);
        return "employeepage";
    }

    @GetMapping("/project/{id}/employees")
    public String getAllEmployeesToAssign(@PathVariable int id, Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println(employees.size());
        model.addAttribute("allEmployees", employees);
        return "assignEmployee";
    }

    //add employee to project
    @PostMapping("/project/{id}/employee/add/project") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToProject(@PathVariable("id") int projectID, @RequestParam("employeeID") int employeeID) {
            employeeService.addExistingEmployeeToProject(employeeID, projectID);
            session.getAttribute("projectID");
            return "redirect:/project/employees";
    }

    //add employee to task
   /* @PostMapping("/project/employee/add/task") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToTask(@ModelAttribute Employee employee, @RequestParam int projectID, @RequestParam int taskID) {
        employeeService.addExistingEmployeeToTask(employee, taskID, projectID);
        session.getAttribute("taskID");
        session.getAttribute("projectID");
        return "redirect: /project/employees";
    }

    //add employee to subtask
    @PostMapping("/project/employee/add/subtask") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToSubTask(@ModelAttribute Employee employee, @RequestParam int projectID, @RequestParam int taskID, @RequestParam int subTaskID) {
        employeeService.addExistingEmployeeToSubTask(employee, taskID, subTaskID, projectID);
        session.getAttribute("taskID");
        session.getAttribute("projectID");
        session.getAttribute("subTaskID");
        return "redirect: /project/employees";
    }*/

        //update
        @PostMapping("/project/employee/edit")
        public String editEmployee (Employee employee){ //@RequestParam int employeeID ??
            employeeService.editEmployee(employee);
            return "redirect:/project/employees";
        }

        //delete
        @PostMapping("/project/employee/delete")
        public String deleteEmployee (@RequestParam int employeeID){
            employeeService.deleteEmployee(employeeID);
            return "redirect:/project/employees";
        }
}