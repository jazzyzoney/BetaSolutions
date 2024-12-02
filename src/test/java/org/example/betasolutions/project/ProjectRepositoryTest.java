package org.example.betasolutions.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class ProjectRepositoryTest{

    @Autowired
    ProjectRepository projectRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testInsertObjectIntoTable() {
        Project project = new Project(1, "Project1", "Owner1", 10, 10, 100.0, Date.valueOf("2015-10-10"), Date.valueOf("2015-10-10"));
        /*
        int actualInt = projectRepository.insertAssignmentIntoTable()//(project); //method returns 1 if successfull, 0 if failed.
        int expectedInt = 1;
        assertEquals(expectedInt, actualInt);*/
    }
    @Test
    void TestRepositoryNotNull() {
        assertNotNull(projectRepository);
    }

    @Test
    void testReadAllTasks() {


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