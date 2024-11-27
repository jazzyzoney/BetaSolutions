package org.example.betasolutions;

import java.sql.Date;

public class TaskTypes {

    protected int id;
    protected int days;
    protected int hours;

    protected String name;
    protected Date startDate;
    protected Date endDate; //datatype???

    protected double totalPrice;


    public TaskTypes(int id, String name, int hours, int days, double totalPrice, Date endDate, Date startDate){
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.days = days;
        this.totalPrice = totalPrice;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    String getName(){
        return name;
    }
    Date getEndDate(){
        return endDate;
    }
    Date getStartDate(){
        return startDate;
    }

    void setName(String name){
        this.name = name;
    };


    void setEndDate(Date endDate){};
    void setStartDate(Date startDate){};


}
