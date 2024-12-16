package org.example.betasolutions.subTask;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.TimeManager;

import java.sql.Date;
import java.sql.Time;

public class SubTask implements ModelInterface {
    private int subTaskID;
    private String name;
    private int hours;
    private int totalDays;
    private double totalPrice;
    private Date deadline;
    private Date startDate;

    private int taskID;

    //empty
    public SubTask() {
    }


    //set all values.
    public SubTask (String name, int hours, double price, Date startDate, int taskID){
        this.name = name;
        totalPrice = price;
        this.startDate = startDate;
        this.hours = hours;
        this.taskID = taskID;

        TimeManager timeManager = new TimeManager();
        totalDays = timeManager.calculateDays(hours);
        deadline = timeManager.calculateEndDate(startDate, totalDays);
    }

    //factory
    public SubTask (int subTaskID, String name, int hours, int days, double price, Date deadLine , Date startDate){
        this.subTaskID = subTaskID;
        this.name = name;
        this.hours = hours;
        totalDays = days;
        totalPrice = price;
        this.startDate = startDate;
        deadline = deadLine;

    }

    public int getID() {
        return subTaskID;
    }

    public void setSubTaskID(int subTaskID) {
        this.subTaskID = subTaskID;
    }

    public String getName() {
        return name;
    }

    public void setName(String subTaskName) {
        this.name = subTaskName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours){

        try {
            this.hours = hours;
            //right here oficer this is where the error is
            // fucking theimleaf
            //TimeManager timeManager = new TimeManager();

            //totalDays = timeManager.calculateDays(hours);
           // deadline = timeManager.calculateEndDate(startDate, totalDays);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setotalPrice(double subTaskTotalPrice) {
        this.totalPrice = subTaskTotalPrice;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public int getTaskID() {
        return taskID;
    }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
}
