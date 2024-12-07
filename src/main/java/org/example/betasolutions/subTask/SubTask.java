package org.example.betasolutions.subTask;

import org.example.betasolutions.ModelInterface;

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

    public SubTask() {
    }

    public SubTask(int subTaskID, String subTaskName, int subTaskTotalHours, int subTaskTotalDays, double subTaskTotalPrice, Date subTaskDeadline, Date subTaskStartDate, int taskID) {
        this.subTaskID = subTaskID;
        this.subTaskName = subTaskName;
        this.subTaskTotalHours = subTaskTotalHours;
        this.subTaskTotalDays = subTaskTotalDays;
        this.subTaskTotalPrice = subTaskTotalPrice;
        this.subTaskDeadline = subTaskDeadline;
        this.subTaskStartDate = subTaskStartDate;
        this.taskID = taskID;
    }
    public SubTask(int subTaskID, String subTaskName, int subTaskTotalHours, int subTaskTotalDays, double subTaskTotalPrice, Date subTaskDeadline, Date subTaskStartDate) {
        this.subTaskID = subTaskID;
        this.subTaskName = subTaskName;
        this.subTaskTotalHours = subTaskTotalHours;
        this.subTaskTotalDays = subTaskTotalDays;
        this.subTaskTotalPrice = subTaskTotalPrice;
        this.subTaskDeadline = subTaskDeadline;
        this.subTaskStartDate = subTaskStartDate;
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

    public void setSubTaskTotalHours(int subTaskTotalHours) {
        this.subTaskTotalHours = subTaskTotalHours;
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
