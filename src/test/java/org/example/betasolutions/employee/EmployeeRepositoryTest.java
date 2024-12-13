package org.example.betasolutions.employee;

import jakarta.transaction.Transactional;
import org.example.betasolutions.ConnectionManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ConnectionManager connectionManager;

    private Connection conn;

    @BeforeEach
    void setUp(){
        conn = connectionManager.getConnection();

    }

    @AfterEach
    void tearDown(){
        try{
            conn.rollback();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    void createNewEmployee() {
        boolean actual = employeeRepository.createNewEmployee(new Employee(1, "John Doe", "Office", "Proficient", "1000"));
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void getAllEmployees() {
        int actual = employeeRepository.getAllEmployees().size();
        int expected = 2;
        assertEquals(expected, actual);
        String actualName = employeeRepository.getAllEmployees().get(0).getEmployeeName();
        String expectedName = "Employee 1";
        assertEquals(expectedName, actualName);
    }

    @Test
    void getAllEmployeesForProject() {
        int actual = employeeRepository.getAllEmployeesForProject(1).size();
        int expected = 1;
        assertEquals(expected, actual);
        String actualName = employeeRepository.getAllEmployeesForProject(1).get(0).getEmployeeName();
        String expectedName = "Employee 1";
    }

    @Test
    void getAllEmployeesNotOnProject() {
        int actual = employeeRepository.getAllEmployeesNotOnProject(1).size();
        int expected = 1;
        assertEquals(expected, actual);
        String actualName = employeeRepository.getAllEmployeesNotOnProject(1).get(0).getEmployeeName();
        String expectedName = "Employee 2";
    }

    @Test
    void addExistingEmployeeToProject() {
        employeeRepository.addExistingEmployeeToProject(1, 2);
        int  actual = employeeRepository.getAllEmployeesForProject(2).size();
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void addExistingEmployeeToTask() {
        employeeRepository.addExistingEmployeeToTask(1, 2, 2);
        int actual = employeeRepository.getAllEmployees().size();
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void addExistingEmployeeToSubTask() {
        employeeRepository.addExistingEmployeeToSubTask(1, 2, 2, 2);
        int actual = employeeRepository.getAllEmployees().size();
        int expected = 2;
        assertEquals(expected, actual);
    }
}