package org.example.betasolutions.subProject;

import org.example.betasolutions.TimeManager;

import java.sql.Date;

import org.example.betasolutions.ModelInterface;

public class SubProject implements ModelInterface {

    int subProjectID;
    String subProjectName;

    int subProjectTotalHours;
    int subProjectTotalDays;
    double subProjectTotalPrice;
    Date subProjectStartDate;
    Date subProjectDeadline;

    int projectID;

    public SubProject(){}

    //factory construcor
    public SubProject(int id, String name, int hours, int days, double price, Date startDate, Date deadLine){
        subProjectID = id;
        subProjectName = name;
        subProjectTotalHours = hours;
        subProjectTotalDays = days;
        subProjectTotalPrice = price;
        subProjectStartDate = startDate;
        subProjectDeadline = deadLine;
    }

    //set all variables:
    public SubProject(String name, int hours, double price, Date startDate, int projectID){
        subProjectName = name;
        subProjectTotalPrice = price;
        subProjectStartDate = startDate;

        this.projectID = projectID;

        setHours(hours);
    }


    public void setID(int subProjectID) {
        this.subProjectID = subProjectID;
    }
    public int getID() {
        return subProjectID;
    }
    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    public int getProjectID() {
        return projectID;
    }
    public void setName(String subProjectName) {
        this.subProjectName = subProjectName;
    }
    public String getName() {
        return subProjectName;
    }
    public void setHours(int subProjectTotalHours) {
        this.subProjectTotalHours = subProjectTotalHours;

    }
    public int getHours() {
        return subProjectTotalHours;
    }
    public void setDays(int subProjectTotalDays) {
        this.subProjectTotalDays = subProjectTotalDays;
    }
    public int getDays() {
        return subProjectTotalDays;
    }
    public void setTotalPrice(double subProjectTotalPrice) {
        this.subProjectTotalPrice = subProjectTotalPrice;
    }
    public double getTotalPrice() {
        return subProjectTotalPrice;
    }
    public void setDeadline(Date subProjectDeadline) {
        this.subProjectDeadline = subProjectDeadline;
    }
    public Date getDeadline() {
        return subProjectDeadline;
    }

    public void setStartDate(Date subProjectStartDate) {
        this.subProjectStartDate = subProjectStartDate;
    }
    public Date getStartDate() {
        return subProjectStartDate;
    }



}
