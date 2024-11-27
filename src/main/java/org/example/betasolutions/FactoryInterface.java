package org.example.betasolutions;
import java.sql.Date;

@FunctionalInterface
public interface FactoryInterface {

    ModelInterface create(int id, String name, int hours, int days, double totalPrice, Date endDate, Date startDate);
}
