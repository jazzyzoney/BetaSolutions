package org.example.betasolutions;
import java.sql.Date;

@FunctionalInterface
public interface FactoryInterface {

    ModelInterface build(int id, String name, int hours, int days, double totalPrice, Date endDate, Date startDate);
}
