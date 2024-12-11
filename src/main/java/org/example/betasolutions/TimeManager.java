package org.example.betasolutions;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class TimeManager {

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
        for (int i = 0; i < days; i++){
            localDate = localDate.plusDays(1); //add one day at a time.
            //if the day is a Sunday or Saturday, it is skipped, since it's the weekend.
            if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
            localDate.getDayOfWeek() == DayOfWeek.SUNDAY){
                days++;
               continue;
            }


        } //end of loop.

        Date deadline = Date.valueOf(localDate);//convert localdate object back into sql.Date object.

        return deadline;
    }
}
