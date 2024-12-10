package org.example.betasolutions.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void createNewEmployee() {
        boolean actual = employeeRepository.createNewEmployee(new Employee(1, "John Doe", "Office", "Proficient", "1000"));
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void getAllEmployees() {

    }

    @Test
    void getAllEmployeesForProject() {
    }

    @Test
    void getAllEmployeesNotOnProject() {
    }

    @Test
    void addExistingEmployeeToProject() {
    }

    @Test
    void addExistingEmployeeToTask() {
    }

    @Test
    void addExistingEmployeeToSubTask() {
    }
}