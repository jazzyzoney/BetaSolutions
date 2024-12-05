package org.example.betasolutions.employee;

import org.example.betasolutions.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final Connection conn;

    public EmployeeRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //create
    public int createNewEmployee(Employee employee) {
        String sql = "INSERT INTO employee (employee_id, employee_name, employee_office, employee_proficiency, employee_salary) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setString(2, employee.getEmployeeOffice());
            preparedStatement.setString(3, employee.getEmployeeProficiency());
            preparedStatement.setString(4, employee.getEmployeeSalary());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            System.out.println("New employee created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("New employee not created");
        return 1;
    }

    //read
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> employees = new ArrayList<>();

            while (resultSet.next()) {
                int employeeID = resultSet.getInt("employee_id");
                String employeeName = resultSet.getString("employee_name");
                String employeeOffice = resultSet.getString("employee_office");
                String employeeProficiency = resultSet.getString("employee_proficiency");
                String employeeSalary = resultSet.getString("employee_salary");
                employees.add(new Employee(employeeID, employeeName, employeeOffice, employeeProficiency, employeeSalary));
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //add existing employee to project_employee table
    public void addExistingEmployeeToProject(int employeeID, int projectID) { //idName is the name of the column
        String sql = "INSERT INTO project_employee_task (employee_id, project_id) VALUES (?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, employeeID);
            preparedStatement.setInt(2, projectID);
            preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Employee added to project");
            } else {
                System.out.println("Employee not added to project");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //add existing employee to project_employee_task table
    public void addExistingEmployeeToTask(Employee employee, int taskID, int projectID) { //idName is the name of the column
        String sql = "INSERT INTO project_employee_task (employee_id, project_id, task_id) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, employee.getEmployeeID());
            preparedStatement.setInt(2, projectID);
            preparedStatement.setInt(3, taskID);
            preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Employee added to task");
            } else {
                System.out.println("Employee not added to task");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //add existing employee to project_employee_task_subTask table
    public void addExistingEmployeeToSubTask(Employee employee, int projectID, int taskID, int subTaskID) { //idName is the name of the column
        String sql = "INSERT INTO project_employee_task_subTask (employee_id, project_id, task_id, sub_task_id) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, employee.getEmployeeID());
            preparedStatement.setInt(2, projectID);
            preparedStatement.setInt(3, taskID);
            preparedStatement.setInt(4, subTaskID);
            preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Employee added to subtask");
            } else {
                System.out.println("Employee not added to subtask");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update
    public void editEmployee(Employee employee) {
        String sql = "UPDATE employee SET employee_name = ?, employee_office = ?, employee_proficiency = ?, employee_salary = ? WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setString(2, employee.getEmployeeOffice());
            preparedStatement.setString(3, employee.getEmployeeProficiency());
            preparedStatement.setString(4, employee.getEmployeeSalary());
            preparedStatement.setInt(5, employee.getEmployeeID());
            preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Employee updated");
            } else {
                System.out.println("Employee not updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete
    public void deleteEmployee(int employeeID) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, employeeID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Employee deleted");
            } else {
                System.out.println("Employee not deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}