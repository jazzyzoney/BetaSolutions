package org.example.betasolutions.task;
import org.example.betasolutions.ModelInterface;
import java.sql.Date;

public class Task implements ModelInterface {
    private int id;
    private String name;
    private int hours;
    private int days;
    private double totalPrice;
    private Date Deadline;
    private Date startDate;

    private int subProjectID;
    private int projectID;

    public Task(int id, String name, int hours, int days, double totalPrice, Date Deadline, Date startDate) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.days = days;
        this.totalPrice = totalPrice;
        this.Deadline = Deadline;
        this.startDate = startDate;
    }

    public Task(String taskName, Date taskStartDate){
        this.startDate = taskStartDate;
        this.name = taskName;
    }

    public Task(String taskName, Date taskStartDate, Date taskDeadLine){
        this.startDate = taskStartDate;
        this.name = taskName;
        this.Deadline = taskDeadLine;
    }

    public Task (){}

    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
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
    public void setDeadline(Date endDate) {
        this.Deadline = endDate;
    }
    public Date getDeadline() {
        return Deadline;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setTotalHours(int hours){
        this.hours = hours;
    }

    public void setTotalDays(int days){
        this.days = days;
    }

    public void setSubProjectID(int subProjectID){
        this.subProjectID = subProjectID;
    }

    public void setProjectID(int projectID){
        this.projectID = projectID;
    }
}
