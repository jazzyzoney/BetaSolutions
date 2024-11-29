package org.example.betasolutions;

import org.example.betasolutions.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class PSSTSuperclassTest {

    private Task task;

    @Autowired
    @Qualifier("projectRepository") // Specify the exact bean name
    PSSTSuperclass superRepository;


    @BeforeEach
    void setUp() {
        //task = new Task (1, "supersej task", 43,8, 50000.0,
                //new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 70000000));
    }

    @Test
    void readAllTasks() {
        assertTrue(false);

    }

    @Test
    void readAllTasksForEmployee() {
        assertTrue(false);
    }

    @Test
    void insertObjectIntoTable() {
       /* int actualID = superRepository.insertObjectIntoTable(task, "task", "supersejt project", 43, 8, 50000.5);
        int expectedID = 2;
        assertEquals(actualID, expectedID);*/
        assertTrue (false);
    }

    @Test
    void testReadAllTasks() {
        List<ModelInterface> actualTaskList = superRepository.readAllTasks("task", 1, "task", Task::new);
        String actualTaskName = actualTaskList.get(0).getName();
        String expectedTaskName = "Task 1";

        assertEquals(expectedTaskName, actualTaskName); //verify name is same as in database.

        int taskID1 = actualTaskList.get(0).getID();
        int taskID2 = actualTaskList.get(1).getID();

        assertNotEquals(taskID1, taskID2); //verify ID's are unique.
    }

    @Test
    void testReadAllTasksForEmployee() {/*
        List<ModelInterface> actualTaskList = superRepository.readAllTasksForEmployee("task",1, 1, "task", Task::new);
        String actualTaskName = actualTaskList.get(0).getName();
        String expectedTaskName = "Task 1";

        assertEquals(expectedTaskName, actualTaskName); //verify name is same as in database.

        int taskID1 = actualTaskList.get(0).getID();
        int taskID2 = actualTaskList.get(1).getID();

        assertNotEquals(taskID1, taskID2); //verify ID's are unique.*/

        assertTrue(false);

    }

    @Test
    void deleteObjectFromTable() {
        boolean objectDeleted = false;/*
        try {
            superRepository.conn.setAutoCommit(false);
            objectDeleted = superRepository.deleteObjectFromTable("task", "task", 1, 1);
            //superRepository("sub")
            superRepository.conn.commit();
            superRepository.conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        */

        assertTrue(objectDeleted);
    }

    @Test
    void updateObjectAtrribute() {
        
    }
}