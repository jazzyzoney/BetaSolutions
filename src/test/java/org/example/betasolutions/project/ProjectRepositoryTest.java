package org.example.betasolutions.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

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
        //projectRepository.insertObjectIntoTable(ModelInterface object, String tableName, String name, int hours, int days, int totalPrice)
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
        int actualID = projectRepository.createProject("det sejeste projekt i hele verden", "test PO", 80, 6, 100000.50);
        int expectedID = 2;

        assertEquals(expectedID, actualID);

    }
}