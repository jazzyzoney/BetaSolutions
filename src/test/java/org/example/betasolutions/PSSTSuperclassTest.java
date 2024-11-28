package org.example.betasolutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class PSSTSuperclassTest {

    @Autowired
    @Qualifier("projectRepository") // Specify the exact bean name
    PSSTSuperclass superRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {

    }

    @Test
    void readAllTasks() {
    }

    @Test
    void readAllTasksForEmployee() {
    }

    @Test
    void insertObjectIntoTable() {
    }

    @Test
    void testReadAllTasks() {
    }

    @Test
    void testReadAllTasksForEmployee() {
    }

    @Test
    void deleteObjectFromTable() {
    }

    @Test
    void updateObjectAtrribute() {
    }
}