package org.example.betasolutions.subProject;

import org.example.betasolutions.ModelInterface;


import java.sql.Date;
import java.time.LocalDate;


public class SubProject implements ModelInterface {
    private int subProjectID;
    private String projectName;
    private int projectTotalHours;
    private int projectTotalDays;
    private double projectTotalPrice;
    private Date projectDeadline;
    private Date projectStartDate;
    private int projectID;


    public SubProject(int subProjectID, String projectName, int projectTotalHours, int projectTotalDays, double projectTotalPrice, Date projectStartDate, Date projectDeadline) {
        this.subProjectID = subProjectID;
        this.projectName = projectName;
        this.projectTotalHours = projectTotalHours;
        this.projectTotalDays = projectTotalDays;
        this.projectTotalPrice = projectTotalPrice;
        this.projectDeadline = projectDeadline;
        this.projectStartDate = projectStartDate;

    }
    public SubProject(int subProjectID, String projectName, int projectTotalHours, int projectTotalDays, double projectTotalPrice, Date projectStartDate, Date projectDeadline, int projectID) {
        this.subProjectID = subProjectID;
        this.projectName = projectName;
        this.projectTotalHours = projectTotalHours;
        this.projectTotalDays = projectTotalDays;
        this.projectTotalPrice = projectTotalPrice;
        this.projectDeadline = projectDeadline;
        this.projectStartDate = projectStartDate;
        this.projectID = projectID;
    }

    //constructor with no subprojectID.
    public SubProject(String projectName, int projectTotalHours, int projectTotalDays, double projectTotalPrice, Date projectStartDate, Date projectDeadline, int projectID) {
        this.projectName = projectName;
        this.projectTotalHours = projectTotalHours;
        this.projectTotalDays = projectTotalDays;
        this.projectTotalPrice = projectTotalPrice;
        this.projectDeadline = projectDeadline;
        this.projectStartDate = projectStartDate;
        this.projectID = projectID;
    }

    public SubProject() {
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
    public void setName(String projectName) {
        this.projectName = projectName;
    }
    public String getName() {
        return projectName;
    }
    public void setHours(int projectTotalHours) {
        this.projectTotalHours = projectTotalHours;
    }
    public int getHours() {
        return projectTotalHours;
    }
    public void setDays(int projectTotalDays) {
        this.projectTotalDays = projectTotalDays;
    }
    public int getDays() {
        return projectTotalDays;
    }
    public void setTotalPrice(double projectTotalPrice) {
        this.projectTotalPrice = projectTotalPrice;
    }
    public double getTotalPrice() {
        return projectTotalPrice;
    }
    public void setDeadline(Date projectDeadline) {
        this.projectDeadline = projectDeadline;
    }
    public Date getDeadline() {
        return projectDeadline;
    }

    /*
    public LocalDate getDeadlineAsLocalDate(){
        return projectDeadline.toLocalDate();
    }*/
    public void setStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
    public Date getStartDate() {
        return projectStartDate;
    }



}
