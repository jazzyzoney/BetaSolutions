package org.example.betasolutions.task;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.TimeManager;

import java.sql.Date;

public class Task implements ModelInterface {

    private int id;
    private String name;
    private int hours;
    private int days;
    private double totalPrice;

    private Date taskDeadLine;
    private Date taskStartDate;
    private int totalHours;
    private int totalDays;
    private int subProjectID;
    private int projectID;

    //empty
    public Task(){}

    //factory constructor.
    public Task(int id, String name, int hours, int days, double totalPrice, Date Deadline, Date startDate) {
        this.id = id;
        this.name = name;
        this.totalHours = hours;
        this.totalDays = days;
        this.totalPrice = totalPrice;
        this.taskDeadLine = Deadline;
        this.taskStartDate = startDate;
    }


    //set all values with subproject.
    public Task(String name, int totalHours, double totalPrice, Date taskStartDate, int projectID, int subProjectID){
        //this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.taskStartDate = taskStartDate;

        setHours(totalHours);

        this.projectID = projectID;
        this.subProjectID = subProjectID;
    }

    //set all values without subproject.
    public Task(String name, int totalHours, double totalPrice, Date taskStartDate, int projectID){
        //this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.taskStartDate = taskStartDate;

        setHours(totalHours);

        this.projectID = projectID;
    }

    public String getName(){
        return name;
    }

    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getTotalDays() {
        return totalDays;
    }
    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice() {
        return totalPrice;
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

    public void setHours(int hours){
        try {
        //TimeManager timeManager = new TimeManager();
        this.hours = hours;

        //days = timeManager.calculateDays(hours);
        //taskDeadLine = timeManager.calculateEndDate(taskStartDate, days);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setSubProjectID(int subProjectID){
        this.subProjectID = subProjectID;
    }

    public void setProjectID(int projectID){
        this.projectID = projectID;
    }

    public int getHours(){
        return hours;
    }

    public int getDays(){
        return days;
    }
    public void setDays(int days){
        this.days = days;
    }

    public void setTotalHours(int totalHours){
        this.totalHours = totalHours;
    }

    public int getTotalHours(){
        return totalHours;
    }
}
