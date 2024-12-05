package org.example.betasolutions.subProject;

import org.example.betasolutions.ModelInterface;


import java.sql.Date;


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
    public SubProject() {
    }

    public void setID(int projectID) {
        this.projectID = projectID;
    }
    public int getID() {
        return projectID;
    }
    public void setSubProjectID(int subProjectID) {
        this.subProjectID = subProjectID;
    }
    public int getSubProjectID() {
        return subProjectID;
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
    public void setStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
    public Date getStartDate() {
        return projectStartDate;
    }
}
