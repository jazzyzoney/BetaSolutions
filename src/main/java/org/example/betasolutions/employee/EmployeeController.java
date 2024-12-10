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

    //create new employee - get the form to create a new employee
    @GetMapping("/employee/new")
    public String createNewEmployee(Model model) {
        Employee employee = new Employee();
        employeeService.createNewEmployee(employee);
        model.addAttribute("employee", employee);
        return "newEmployee";
    }

    //post the new employee and go back to the employee page
    @PostMapping("/employee/new/post")
    public String createNewEmployeePost(Model model, @ModelAttribute Employee employee) { //henter model fra getmapping overfor
        employeeService.createNewEmployee(employee);
        model.addAttribute("employee", employee);
        return "redirect:/employees";
    }

    //read all employees in the business and show them on the employee page in a list
    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println(employees.size());
        model.addAttribute("allEmployees", employees);
        return "employeepage";
    }

    //read employees that are assigned to a specific project
    @GetMapping("/project/{id}/employees") //get employees for specific project
    public String getAllEmployeesForProject(@PathVariable int id, Model model) {
        List<Employee> employees = employeeService.getAllEmployeesForProject(id);
        System.out.println(employees.size());
        model.addAttribute("allEmployees", employees);
        return "allEmployeesOnProject";
    }

    //read all the employees that are NOT assigned to the specific project
    @GetMapping("/project/{id}/employeesNotAssigned") //get employees NOT on the specific project
    public String getAllEmployeesNotOnProject(@PathVariable int id, Model model) {
        List<Employee> employees = employeeService.getAllEmployeesNotOnProject(id);
        System.out.println(employees.size());
        model.addAttribute("allEmployees", employees);
        return "allEmployeesNotOnProject";
    }

    //add employee to project
    @PostMapping("/project/{id}/employee/add/project") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToProject(@PathVariable("id") int projectID, @RequestParam("employeeID") int employeeID) {
            employeeService.addExistingEmployeeToProject(employeeID, projectID);
            session.getAttribute("projectID");
            return "redirect:/project/" + projectID;
    }

    //add employee to task
    @PostMapping("/project/{id}/employee/add/task") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToTask(@PathVariable("id") int projectID, @RequestParam("employeeID") int employeeID, @RequestParam("taskID") int taskID) {
        employeeService.addExistingEmployeeToTask(employeeID, taskID, projectID);
        session.getAttribute("taskID");
        session.getAttribute("projectID");
        return "redirect:/project/employees";
    }

    //add employee to subtask
    @PostMapping("/project/{id}/employee/add/subtask") //add employee to project/subproject/task/subtask aka assignment
    public String addExistingEmployeeToSubTask(@PathVariable("id") int projectID, @RequestParam("employeeID") int employeeID, @RequestParam("taskID") int taskID, @RequestParam("subTaskID") int subTaskID) {
        employeeService.addExistingEmployeeToSubTask(employeeID, taskID, subTaskID, projectID);
        session.getAttribute("taskID");
        session.getAttribute("projectID");
        session.getAttribute("subTaskID");
        return "redirect:/project/employees";
    }

    //update - not in use yet, so no test for this method
    @PostMapping("/project/employee/edit")
    public String editEmployee (Employee employee){ //@RequestParam int employeeID ??
         employeeService.editEmployee(employee);
         return "redirect:/project/employees";
    }

    //delete - not in use yet, so no test for this method
    @PostMapping("/project/employee/delete")
    public String deleteEmployee (@RequestParam int employeeID){
         employeeService.deleteEmployee(employeeID);
         return "redirect:/project/employees";
    }
}