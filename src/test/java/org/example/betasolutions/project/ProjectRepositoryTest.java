package org.example.betasolutions.project;
import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.ModelInterface;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class ProjectRepositoryTest{

    @Autowired
    ProjectRepository projectRepository;

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

    @Test
    void contextLoads() {
    }

    @Test
    void testInsertObjectIntoTable() {

        //new project
        Project project = new Project("projectName", "projectOwner", 43,
                500000.5, Date.valueOf("2024-12-02"));

        int actualID = projectRepository.insertAssignmentIntoTable(project); //insert project object into sql table.
        int expectedID = 3;
        assertEquals(expectedID, actualID);

        int secondActualID = projectRepository.insertAssignmentIntoTable(project); //insert same project into table again.
        int secondExpectedID = 4;
        assertEquals(secondExpectedID, secondActualID);

        assertNotEquals(actualID, secondActualID); //autoincrement

    }
    @Test
    void TestRepositoryNotNull() {
        assertNotNull(projectRepository);
    }

    @Test
    void testReadAllTasks() {
        List<Project> projectList = projectRepository.readAllProjects();
        assertNotNull(projectList);
        assertEquals(2, projectList.size());
        assertNotNull(projectList.get(0).getProjectOwner());
        assertNotNull(projectList.get(1).getProjectOwner());



    }

    @Test
    void testReadAllTasksForEmployee() {
    }

    @Test
    void testDeleteObjectFromTable() {
    }

    @Test
    void testUpdateObjectAtrribute() {
    }

    //projectRepository
    @Test
    void createProject() {

    }

}