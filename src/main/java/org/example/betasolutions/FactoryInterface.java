package org.example.betasolutions;
import java.sql.Date;

@FunctionalInterface
public interface FactoryInterface {
    // might wanna add active status to the build method
    ModelInterface build(int projectID, String projectName, int projectTotalHours, int projectTotalDays, double projectTotalPrice, Date projectDeadline, Date projectStartDate);


}
