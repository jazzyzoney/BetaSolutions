package org.example.betasolutions.subTask;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.TimeManager;

import java.sql.Date;

public class SubTask implements ModelInterface {
    private int subTaskID;
    private String subTaskName;
    private int subTaskTotalHours;
    private int subTaskTotalDays;
    private double subTaskTotalPrice;
    private Date subTaskDeadline;
    private Date subTaskStartDate;
    private int taskID;

    //empty
    public SubTask() {
    }

    public SubTask (String name, int hours, double price, Date startDate, int taskID){
        subTaskName = name;
        subTaskTotalPrice = price;
        subTaskStartDate = startDate;

        setHours(hours);

        this.taskID = taskID;
    }

    //factory
    public SubTask (int subTaskID, String name, int hours, int days, double price, Date deadLine , Date startDate){
        this.subTaskID = subTaskID;
        subTaskName = name;
        subTaskTotalHours = hours;
        subTaskTotalDays = days;
        subTaskTotalPrice = price;
        subTaskStartDate = startDate;
        subTaskStartDate = deadLine;

    }

    public int getID() {
        return subTaskID;
    }

    public void setSubTaskID(int subTaskID) {
        this.subTaskID = subTaskID;
    }

    public String getName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public int getHours() {
        return subTaskTotalHours;
    }

    /*
    public void setSubTaskTotalHours(int subTaskTotalHours) {
        this.subTaskTotalHours = subTaskTotalHours;
    }
*/

    public void setHours(int hours){
        this.subTaskTotalHours = hours;

        TimeManager timeManager = new TimeManager();

        subTaskTotalDays = timeManager.calculateDays(hours);
        subTaskDeadline = timeManager.calculateEndDate(subTaskStartDate, subTaskTotalDays);
    }

    public int getDays() {
        return subTaskTotalDays;
    }

    public void setSubTaskTotalDays(int subTaskTotalDays) {
        this.subTaskTotalDays = subTaskTotalDays;
    }

    public double getTotalPrice() {
        return subTaskTotalPrice;
    }

    public void setSubTaskTotalPrice(double subTaskTotalPrice) {
        this.subTaskTotalPrice = subTaskTotalPrice;
    }

    public Date getDeadline() {
        return subTaskDeadline;
    }

    public void setSubTaskDeadline(Date subTaskDeadline) {
        this.subTaskDeadline = subTaskDeadline;
    }

    public Date getStartDate() {
        return subTaskStartDate;
    }
    public void setSubTaskStartDate(Date subTaskStartDate) {
        this.subTaskStartDate = subTaskStartDate;
    }
    public int getTaskID() {
        return taskID;
    }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
}
