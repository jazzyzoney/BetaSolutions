package org.example.betasolutions;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimeManagerTest {
    private final TimeManager timeManager = new TimeManager();

    @Test
    void calculateDays() {
        assertEquals(2, timeManager.calculateDays(12));
        //i will see if 3 days is returned when 13 hours are given.because 13/6 = 2.1 which is 3 days in the method cos of %
        assertEquals(3, timeManager.calculateDays(13));

    }

    @Test
    void calculateEndDate() {
        // this test will check if the end date is calculated correctly
        Date actualDate= timeManager.calculateEndDate(Date.valueOf("2024-12-10"),2);
        Date exptedDate = Date.valueOf("2024-12-12");
        assertEquals(exptedDate,actualDate);
        // this date will check if the end date is calculated correctly when the end date is a weekend aka 14th of december is a saturday
        actualDate = timeManager.calculateEndDate(Date.valueOf("2024-12-13"),1);
        Date weekenDate = Date.valueOf("2024-12-16");
        //
        assertEquals(weekenDate,actualDate);
    }
}