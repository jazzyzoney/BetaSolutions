package org.example.betasolutions.task;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.TimeManager;

import java.sql.Date;

public class Task implements ModelInterface {

    private int taskID;
    private String taskName;
    private int taskTotalHours;
    private int taskHours;
    private int taskTotalDays;
    private int taskDays;
    private double taskTotalPrice;
    private Date taskDeadLine;
    private Date taskStartDate;

    private int subProjectID;
    private int projectID;

    //empty
    public Task(){}

    //factory constructor.
    public Task(int id, String name, int hours, int days, double totalPrice, Date Deadline, Date startDate) {
        this.taskID = id;
        this.taskName = name;
        this.taskTotalHours = hours;
        this.taskTotalDays = days;
        this.taskTotalPrice = totalPrice;
        this.taskDeadLine = Deadline;
        this.taskStartDate = startDate;
    }


    //set all values with subproject.
    public Task(String taskName, int taskTotalHours, double taskTotalPrice, Date taskStartDate, int projectID, int subProjectID){
        //this.id = id;
        this.taskName = taskName;
        this.taskTotalPrice = taskTotalPrice;
        this.taskStartDate = taskStartDate;


        setHours(taskTotalHours);

        this.projectID = projectID;
        this.subProjectID = subProjectID;
    }

    //set all values without subproject.
    public Task(String taskName, int taskTotalHours, double taskTotalPrice, Date taskStartDate, int projectID){
        //this.id = id;
        this.taskName = taskName;
        this.taskTotalPrice = taskTotalPrice;
        this.taskStartDate = taskStartDate;

        setHours(taskTotalHours);

        this.projectID = projectID;
    }

    public void setID(int id) {
        this.taskID = id;
    }
    public int getID() {
        return taskID;
    }

    public void setName(String name) {
        this.taskName = name;
    }
    public String getName() {
        return taskName;
    }
    public void setHours(int hours) {
        this.taskHours = hours;
        TimeManager timeManager = new TimeManager();

        taskDays = timeManager.calculateDays(hours); //set days.
    }

    public void setTotalHours(int totalHours){
        this.taskTotalHours = totalHours;
        TimeManager timeManager = new TimeManager();

        taskTotalDays = timeManager.calculateDays(totalHours); //set total days.
        taskDeadLine = timeManager.calculateEndDate(taskStartDate, taskTotalDays); //set new deadline
    }

    public int getHours() {
        return taskHours;
    }
    public int getTotalHours() {
        return taskTotalHours;
    }
/*
    public void setTotalHours(int hours){
        TimeManager timeManager = new TimeManager();
        this.taskTotalHours = hours;

        taskTotalDays = timeManager.calculateDays(taskTotalHours);
        taskDeadLine = timeManager.calculateEndDate(taskStartDate, taskTotalDays);
    }*/
    
    public int getSubProjectID() {
        return subProjectID;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getDays() {
        return taskTotalDays;
    }
    public void setTotalPrice(double totalPrice) {
        this.taskTotalPrice = totalPrice;
    }
    public double getTotalPrice() {
        return taskTotalPrice;
    }
    public void setDeadline(Date endDate) {
        this.taskDeadLine = endDate;
    }
    public Date getDeadline() {
        return taskDeadLine;
    }
    public void setStartDate(Date startDate) {
        this.taskStartDate = startDate;
    }
    public Date getStartDate() {
        return taskStartDate;
    }


    public void setSubProjectID(int subProjectID){
        this.subProjectID = subProjectID;
    }

    public void setProjectID(int projectID){
        this.projectID = projectID;
    }
}
