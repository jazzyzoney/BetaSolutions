package org.example.betasolutions;
import java.sql.Date;

@FunctionalInterface
public interface FactoryInterface {
    // might wanna add active status to the build method
    ModelInterface build(int id, String name, int hours, int days, double totalPrice, Date endDate, Date startDate);

}
