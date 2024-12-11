package org.example.betasolutions.subTask;

import org.example.betasolutions.task.Task;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {

    @Test
    void setHours() {
        SubTask subTask = new SubTask("Test subTask.", 4, 1000.0, Date.valueOf("2024-12-11"), 1);

        //Test constructor.
        int expectedHours = 4;
        int actualHours = subTask.getHours();
        assertEquals(expectedHours, actualHours);

        int expectedDays = 1;
        int actualDays = subTask.getDays();
        assertEquals(expectedDays, actualDays);

        Date expectedDeadLine = Date.valueOf("2024-12-12");
        Date actualDeadLine = subTask.getDeadline();
        assertEquals(expectedDeadLine, actualDeadLine);


        //Test setter:
        assertTrue(subTask.getHours()>0);
        subTask.setHours(6);
        assertEquals(6,subTask.getHours());
        assertEquals(1,subTask.getDays());

        //Test setter again.
        subTask.setHours(10);
        expectedHours = 10;
        actualHours =  subTask.getHours();
        assertEquals(expectedHours, actualHours);

        expectedDays = 2; // 10/6 = 2.
        actualDays = subTask.getDays();
        assertEquals(expectedDays, actualDays);

        expectedDeadLine = Date.valueOf("2024-12-13"); //+ 2 days.
        actualDeadLine = subTask.getDeadline();
        assertEquals(expectedDeadLine, actualDeadLine);
    }
}