package org.example.betasolutions.task;
import org.example.betasolutions.ModelInterface;
import java.sql.Date;

public class Task implements ModelInterface {
    private int id;
    private String name;
    private int hours;
    private int days;
    private double totalPrice;
    private Date endDate;
    private Date startDate;

    public Task(int id, String name, int hours, int days, double totalPrice, Date endDate, Date startDate) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.days = days;
        this.totalPrice = totalPrice;
        this.endDate = endDate;
        this.startDate = startDate;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public int getHours() {
        return hours;
    }
    public void setDays(int days) {
        this.days = days;
    }
    public int getDays() {
        return days;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return startDate;
    }

}
