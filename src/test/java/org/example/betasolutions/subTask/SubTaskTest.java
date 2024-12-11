package org.example.betasolutions.subTask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {

    @Test
    void setHours() {
        SubTask subTask = new SubTask(1,"sub1", 3000,2500, 1000.0, java.sql.Date.valueOf("2021-01-01"),java.sql.Date.valueOf("2021-01-01"),1);
        assertTrue(subTask.getHours()>0);
        subTask.setSubTaskTotalHours(6);
        assertEquals(6,subTask.getHours());
        assertEquals(1,subTask.getDays());
    }
}