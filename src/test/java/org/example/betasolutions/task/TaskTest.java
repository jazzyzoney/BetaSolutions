package org.example.betasolutions.task;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void setHours() {
        Task task = new Task ("Test Task", 4, 1000.0, Date.valueOf("2024-12-11"), 1);
        //Test constructor.
        int expectedHours = 4;
        int actualHours = task.getHours();
        assertEquals(expectedHours, actualHours);

        int expectedDays = 1;
        int actualDays = task.getDays();
        assertEquals(expectedDays, actualDays);

        Date expectedDeadLine = Date.valueOf("2024-12-12");
        Date actualDeadLine = task.getDeadline();
        assertEquals(expectedDeadLine, actualDeadLine);

        //Test setter:
        task.setTotalHours(10);
        expectedHours = 10;
        actualHours =  task.getHours();
        assertEquals(expectedHours, actualHours);

        expectedDays = 2; // 10/6 = 2.
        actualDays = task.getDays();
        assertEquals(expectedDays, actualDays);

        expectedDeadLine = Date.valueOf("2024-12-16");
        actualDeadLine = task.getDeadline();
        assertEquals(expectedDeadLine, actualDeadLine);

    }
}