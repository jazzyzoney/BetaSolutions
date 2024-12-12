package org.example.betasolutions.subTask;

import org.example.betasolutions.ModelInterface;

import java.sql.Date;

public class SubTask implements ModelInterface {
    private int id;
    private String name;
    private int hours;
    private int days;
    private double totalPrice;
    private Date Deadline;
    private Date startDate;
    private int taskID;

    public SubTask() {
    }

    public SubTask(int id, String name, int hours, int days, double totalPrice, Date Deadline, Date startDate, int taskID) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.days = days;
        this.totalPrice = totalPrice;
        this.Deadline = Deadline;
        this.startDate = startDate;
        this.taskID = taskID;
    }
    public SubTask(int id, String name, int hours, int days, double totalPrice, Date Deadline, Date startDate) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.days = days;
        this.totalPrice = totalPrice;
        this.Deadline = Deadline;
        this.startDate = startDate;
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDeadline() {
        return Deadline;
    }

    public void setDeadline(Date deadline) {
        this.Deadline = deadline;
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
