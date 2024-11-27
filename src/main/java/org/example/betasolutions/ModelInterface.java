package org.example.betasolutions;
import java.util.Date;

public interface ModelInterface {
        String getName();
        java.sql.Date getEndDate();
        java.sql.Date getStartDate();

        void setName();
        void setEndDate(Date endDate);
        void setStartDate(Date startDate);

    }

