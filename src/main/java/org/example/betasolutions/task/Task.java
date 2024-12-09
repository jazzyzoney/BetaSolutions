package org.example.betasolutions.task;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.TimeManager;

import java.sql.Date;

public class Task implements ModelInterface {

    private int taskID;
    private String taskName;
    private int taskTotalHours;
    private int taskTotalDays;
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

    //set all values
    public Task(String taskName, int taskTotalHours, double taskTotalPrice, Date taskStartDate){
        //this.id = id;
        this.taskName = taskName;
        this.taskTotalHours = taskTotalHours;
        this.taskTotalPrice = taskTotalPrice;
        this.taskStartDate = taskStartDate;

        TimeManager timeManager = new TimeManager();
        taskTotalDays = timeManager.calculateDays(taskTotalHours);
        taskDeadLine = timeManager.calculateEndDate(taskStartDate, taskTotalDays);
    }

    /*
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
    */

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
        this.taskTotalHours = hours;

        TimeManager timeManager = new TimeManager();

        taskTotalDays = timeManager.calculateDays(hours);
        taskDeadLine = timeManager.calculateEndDate(taskStartDate, taskTotalDays);

    }

    public int getHours() {
        return taskTotalHours;
    }



    public int getSubProjectID() {
        return subProjectID;
    }

    public int getProjectID() {
        return projectID;
    }

    /*
    public void setDays(int days) {
        this.taskTotalDays = taskTotalDays;
    }*/

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

    public void setTotalHours(int hours){
        this.taskTotalHours = hours;
    }

    /*
    public void setTotalDays(int days){
        this.taskTotalDays = days;
    }*/

    public void setSubProjectID(int subProjectID){
        this.subProjectID = subProjectID;
    }

    public void setProjectID(int projectID){
        this.projectID = projectID;
    }
}
