package org.example.betasolutions.subTask;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.task.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class SubTaskRepositoryTest {

    @Autowired
    SubTaskRepository subTaskRepository;

    @Autowired
    private ConnectionManager connectionManager;

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
    void addSubTaskToTask() {
    }

    @Test
    void readAllSubTasks() {
    }

    @Test
    void deleteSubTask() {
    }

    @Test
    void updateSubTaskTotalHours(){
        SubTask subTask = new SubTask("test", 4, 10.03, Date.valueOf("2024-12-17"), 1);
        assertTrue(subTaskRepository.updateSubTaskTotalHours(subTask));
        //???

    }


}