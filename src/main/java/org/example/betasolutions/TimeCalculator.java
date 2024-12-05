package org.example.betasolutions;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class TimeCalculator {

    public int calculateDays(int hours){
        int days = hours / 6; //calculate how many days of 6 work hours.

        if (hours % 6 > 0){ //if the remainder is more than 0, add another day.
            days ++;
        }

        return days;
    }

    public Date calculateEndDate(Date startDate, int days){

        LocalDate localDate = startDate.toLocalDate(); //create localDate object, from startDate.

        //adding days to deadline.
        for (int i = 0; i <= days; i++){
            //if the day is a Sunday or Saturday, it is skipped, since no one is working those days.
            if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
            localDate.getDayOfWeek() == DayOfWeek.SUNDAY){
                continue;
            }

            localDate.plusDays(1); //add one day at a time.
        } //end of loop.

        Date deadline = Date.valueOf(localDate);

        return deadline;
    }
}
