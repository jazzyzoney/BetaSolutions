package org.example.betasolutions.task;

import org.example.betasolutions.subProject.SubProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)// can't import. Do we need a dependency?
class TaskServiceTest {

    //@Autowired
    //private MockMvc mockMvc;


    //Mock taskrepo and subprojectservice.
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private SubProjectService subProjectService;


    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp(){
        task = new Task ();
        task.setSubProjectID(1);
        task.setProjectID(1);
        task.setHours(4);
        task.setStartDate(Date.valueOf("2024-12-16"));

    }

    @Test
    void createTaskForProject(){

    }

    @Test
    void createTaskForSubProject(){

    }

    @Test
    void calculateDeadline(){
        assertTrue(true);

    }

    @Test
    void updateTaskTotalHours(){
        assertTrue(true);

    }

}