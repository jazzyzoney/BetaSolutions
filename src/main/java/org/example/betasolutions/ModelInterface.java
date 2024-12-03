package org.example.betasolutions;
import java.sql.Date;

public interface ModelInterface {
        int getID();
        String getName();
        int getHours();
        int getDays();
        double getTotalPrice();
        Date getDeadline();
        Date getStartDate();
}

