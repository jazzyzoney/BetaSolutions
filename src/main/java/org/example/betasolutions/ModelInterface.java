package org.example.betasolutions;

import java.sql.Date;

public interface ModelInterface {
    String getName();
    Date getEndDate();
    Date getStartDate();

    void setName();
    void setEndDate(Date endDate);
    void setStartDate(Date startDate);



}
