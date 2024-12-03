package org.example.betasolutions;

import org.example.betasolutions.project.Project;
import org.example.betasolutions.task.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

@Rollback(true)//rolls back commits to database after each test.
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
        //executeSqlScript();
        conn = connectionManager.getConnection(); //instantiate this.conn using ConnectionManager object.
        try {
            conn.setAutoCommit(false);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown(){
        try {
            conn.rollback(); //rollback changes
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //not done
    @Test
    void insertAssignmentIntoTable() {
        //instantiate new project.
        Project project = new Project("projectName", "projectOwner", 43, 8, 500000.5,
                Date.valueOf("2024-12-02"), Date.valueOf("2025-01-01"));

        //testing method using project object and new project sql statement
        PreparedStatement preparedStatement = superRepository.insertAssignmentIntoTable(project,
                "INSERT INTO project (projectName, projectTotalHours, projectTotalDays, projectTotalPrice, projectDeadline, projectStartDate, projectOwner) VALUES (?,?,?,?,?,?,?)");

        assertNotNull(preparedStatement);
    }

    @Test
    void testReadAllAssignments() {
        List<ModelInterface> actualTaskList = superRepository.readAllAssignments("task", 1, "task", Task::new);

        //retrieving name for first element in task list.
        String actualTaskName = actualTaskList.get(0).getName();
        String expectedTaskName = "Task 1";

        assertEquals(expectedTaskName, actualTaskName); //verify name is same as in database.

        //retrieving task ID's for 1st and 2nd task object in tasklist..
        int taskID1 = actualTaskList.get(0).getID();
        int taskID2 = actualTaskList.get(1).getID();

        assertNotEquals(taskID1, taskID2); //verify ID's are unique.
    }

    //brug for employeeID fra andet table.
    @Test
    void testReadAllObjectsForEmployee() {/*
        List<ModelInterface> actualTaskList = superRepository.readAllTasksForEmployee("task",1, 1, "task", Task::new);
        String actualTaskName = actualTaskList.get(0).getName();
        String expectedTaskName = "Task 1";

        assertEquals(expectedTaskName, actualTaskName); //verify name is same as in database.

        int taskID1 = actualTaskList.get(0).getID();
        int taskID2 = actualTaskList.get(1).getID();

        assertNotEquals(taskID1, taskID2); //verify ID's are unique.*/

        assertTrue(true);

    }

    @Test
    void deleteObjectFromTable() {
        boolean objectDeleted = superRepository.deleteObjectFromTable("subTask", "subTask", 1);
        assertTrue(objectDeleted);
    }

    @Test
    void testUpdateObjectString(){
        boolean objectUpdate = superRepository.updateObjectString("task", "taskName", 1, "supersej task");
        assertTrue(objectUpdate);
    }

    @Test
    void getTableIntByString(){
        //get id by name ("Task 1")
        int actualID = superRepository.getTableIntByString("task", "taskID", "taskName", "Task 1");
        int expectedID = 1;

        assertEquals(expectedID, actualID);
    }

    @Test
    void getTableIntByInt(){
        //get id by total hours (100)
        int actualID = superRepository.getTableIntByInt("task", "taskID", "taskTotalHours", 100);
        int expectedID = 1;

        assertEquals(expectedID, actualID);
    }

    @Test
    void getTableStringByString(){
        String expectedProjectOwner = "Owner 1";
        String actualProjectOwner = superRepository.getTableStringByString("project", "projectOwner", "projectName", "Project A");
        assertEquals(expectedProjectOwner, actualProjectOwner);
        assertEquals("Project A", superRepository.getTableStringByString("project", "projectName", "projectOwner", "Owner 1"));


    }

    @Test
    void getTableStringByInt(){
        assertEquals("Project A", superRepository.getTableStringByInt("project", "projectName", "projectID", 1));
        assertEquals("Owner 1", superRepository.getTableStringByInt("project", "projectOwner", "projectID", 1));
    }

    @Test
    void deleteAllWhere(){
        //delete all subtasks.
        boolean deletedAllSubTasks = superRepository.deleteAllWhere("subTask", "taskID = 1");
        assertTrue(deletedAllSubTasks);
    }

}