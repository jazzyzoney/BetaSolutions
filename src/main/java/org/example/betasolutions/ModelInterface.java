package org.example.betasolutions;
import java.sql.Date;

public interface ModelInterface {
        int getId();
        String getName();
        java.sql.Date getEndDate();
        java.sql.Date getStartDate();

        void setName(String name);
        void setEndDate(Date endDate);
        void setStartDate(Date startDate);

    }

