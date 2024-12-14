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
        String sql = "INSERT INTO employee (employee_name, employee_office, employee_proficiency, employee_salary) VALUES (?,?,?,?)";
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

    //add existing employee to assignment table
    public void addExistingEmployeeToAssignment(Employee employee, String assignment, String idName) { //idName is the name of the column
        String sql = "INSERT INTO assignment (employee_id, "+ idName +") VALUES (?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, employee.getEmployeeID());
            preparedStatement.setString(2, idName);
            preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Employee added to assignment");
            } else {
                System.out.println("Employee not added to assignment");
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
    public List<String> GetAllEmployeeOffices() {
        String sql = "SELECT employee_office FROM employee";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> employeeOffices = new ArrayList<>();
            while (resultSet.next()) {
                String employeeOffice = resultSet.getString("employee_office");
                employeeOffices.add(employeeOffice);
            }
            return employeeOffices;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}