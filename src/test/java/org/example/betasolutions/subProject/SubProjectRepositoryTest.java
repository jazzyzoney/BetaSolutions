package org.example.betasolutions.subProject;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.project.Project;
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
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")

class SubProjectRepositoryTest {

    @Autowired
    SubProjectRepository subProjectRepository;

    @Autowired
    ConnectionManager connectionManager;

    //rollback changes to test database after each test.
    @AfterEach
    void tearDown(){
        try{
            connectionManager.getConnection().rollback();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    void insertSubProject() {
        SubProject subproject = new SubProject("sub 1",200,500,Date.valueOf("1996-06-06"),1);

        boolean subprojectInserted = subProjectRepository.insertSubProject(subproject);

        assertTrue(subprojectInserted); //verify that subproject has been inserted in test database.
    }

    @Test
    void readAllSubProjects() {
        int expectedProjectID = 1;
        List< SubProject> subProjectList = subProjectRepository.readAllSubProjects(expectedProjectID);
        int actualProjectID = subProjectList.get(0).getProjectID();

        assertTrue(subProjectList.size() > 0); // test list not empty.

        assertEquals(expectedProjectID, actualProjectID); //check project ID's are equal.

        int actualID = subProjectList.get(0).getID();
        assertEquals(1, actualID); //assert subprojectID = 1.

    }

    @Test
    void readSubProject() {
        int expectedID = 1;
        SubProject subProject = subProjectRepository.readSubProject(expectedID);
        int actualID = subProject.getID();

        assertEquals(expectedID, actualID); //testing subproject has correct ID.

    }

    @Test
    void deleteSubProject() {
        assertTrue (subProjectRepository.deleteSubProject(1));
    }
}