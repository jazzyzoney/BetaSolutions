package org.example.betasolutions;

import jakarta.transaction.Transactional;
import org.example.betasolutions.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

@Rollback(true)//rolls back commits to database after each test.
@Transactional
class PSSTSuperclassTest {

    //private Task task;


    @Autowired
    @Qualifier("projectRepository") // Specify the exact bean name
    private PSSTSuperclass superRepository;

    @Autowired
    private ConnectionManager connectionManager;
    private Connection conn;


    @BeforeEach
    void setUp() {
        conn = connectionManager.getConnection(); //instantiate this.conn using ConnectionManager object.
    }

    //not done
    @Test
    void insertObjectIntoTable() {
       /* int actualID = superRepository.insertObjectIntoTable(task, "task", "test task", 43, 8, 50000.5);
        int expectedID = 2;
        assertEquals(actualID, expectedID);*/
        assertTrue (false);
    }

    @Test
    void testReadAllObjects() {
        List<ModelInterface> actualTaskList = superRepository.readAllObjects("task", 1, 1, Task::new);
        String actualTaskName = actualTaskList.get(0).getName();
        String expectedTaskName = "Task 1";

        assertEquals(expectedTaskName, actualTaskName); //verify name is same as in database.

        int taskID1 = actualTaskList.get(0).getID();
        int taskID2 = actualTaskList.get(1).getID();

        assertNotEquals(taskID1, taskID2); //verify ID's are unique.
    }

    //brug for employeeID fra andet table.
    @Transactional
    @Test
    void testReadAllObjectsForEmployee() {/*
        List<ModelInterface> actualTaskList = superRepository.readAllTasksForEmployee("task",1, 1, "task", Task::new);
        String actualTaskName = actualTaskList.get(0).getName();
        String expectedTaskName = "Task 1";

        assertEquals(expectedTaskName, actualTaskName); //verify name is same as in database.

        int taskID1 = actualTaskList.get(0).getID();
        int taskID2 = actualTaskList.get(1).getID();

        assertNotEquals(taskID1, taskID2); //verify ID's are unique.*/

        assertTrue(false);

    }

    //this.conn = null.
    @Transactional
    @Test
    void deleteObjectFromTable() {
        boolean objectDeleted = false;
        try {
            conn.setAutoCommit(false);

            //objectDeleted = superRepository.deleteObjectFromTable("task", "task", 1); //harder to delete task, since subtasks can be dependent on it.
            //boolean deletedAllSubTasks = superRepository.deleteAllWhere("subTask", "taskID = 1");
            objectDeleted = superRepository.deleteObjectFromTable("subTask", "subTask", 1);

            conn.commit();
            conn.setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }

        assertTrue(objectDeleted);
    }

    @Test
    void testUpdateObjectString(){
        boolean objectUpdate = false;
        try {
            connectionManager.getConnection().setAutoCommit(false);
            objectUpdate = superRepository.updateObjectString("task", "taskName", 1, "supersej task");

            connectionManager.getConnection().commit();
            connectionManager.getConnection().setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }

        assertTrue(objectUpdate);
    }

    @Transactional
    @Test
    void getTableInt(){
        assertTrue(false);

    }

    @Transactional
    @Test
    void getTableString(){
        assertTrue(false);

    }

    @Transactional
    @Test
    void deleteAllWhere(){
        boolean deletedAllSubTasks = superRepository.deleteAllWhere("subTask", "taskID = 1");
        assertTrue(deletedAllSubTasks);
    }

}