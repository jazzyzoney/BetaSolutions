package org.example.betasolutions.task;


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
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ConnectionManager connectionManager;

    Connection conn;

    @BeforeEach
    void setup(){
        conn = connectionManager.getConnection();
    }

    @AfterEach
    void tearDown(){
        try {
            conn.rollback();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void updateTaskHours() {
        //taskRepository.updateTaskHours(1, 4);

    }

    @Test
    void updateTaskDays() {
    }

    @Test
    void addTaskForProject() {
    }

    @Test
    void addTaskToSubProject() {
    }

    @Test
    void readAllTasks() {
    }

    @Test
    void readAllTasksForSubProject() {
    }

    @Test
    void readTask() {

    }
        
}