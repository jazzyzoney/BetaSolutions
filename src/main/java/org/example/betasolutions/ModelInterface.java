package org.example.betasolutions;
import java.sql.Date;

public interface ModelInterface {
        int getID();
        String getName();
        Date getDeadline();
        Date getStartDate();

        void setName(String name);
        void setDeadline(Date Deadline);
        void setStartDate(Date startDate);

    }

