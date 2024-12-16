package org.example.betasolutions.subTask;

import org.example.betasolutions.ConnectionManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class SubTaskRepositoryTest {

    @Autowired
    SubTaskRepository subTaskRepository;

    @Autowired
    ConnectionManager connectionManager;

    @Test
    void addSubTaskToTask() {
        SubTask subTask = new SubTask ( "new subTask", 2, 500,  Date.valueOf("2024-12-11"), 1);
       // subTask.setTaskID(1);
        boolean subTaskAddedToTask = subTaskRepository.addSubTaskToTask(subTask);

        assertTrue(subTaskAddedToTask);
    }

    @Test
    void readAllSubTasks() {
        List <SubTask> allSubTasksForTask = subTaskRepository.readAllSubTasks(1, 1);

        int expectedSize = 1;
        int actualSize = allSubTasksForTask.size();
        assertEquals(expectedSize, actualSize);

        String expectedName = "SubTask 1";
        String actualName = allSubTasksForTask.get(0).getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void deleteSubTask() {
        boolean subTaskDeleted = subTaskRepository.deleteSubTask(1);
        assertTrue(subTaskDeleted);
    }
}