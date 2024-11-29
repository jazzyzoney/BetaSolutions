package org.example.betasolutions.project;
import org.example.betasolutions.ModelInterface;
import java.sql.Date;

public class Project implements ModelInterface {
    private int projectID;
    private String projectName;
    private String projectOwner;
    private int projectTotalHours;
    private int projectTotalDays;
    private double projectTotalPrice;
    private Date projectDeadline;
    private Date projectStartDate;

    public Project(int projectID, String projectName, String projectOwner, int projectTotalHours, int projectTotalDays, double projectTotalPrice, Date projectDeadline, Date projectStartDate) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.projectTotalHours = projectTotalHours;
        this.projectTotalDays = projectTotalDays;
        this.projectTotalPrice = projectTotalPrice;
        this.projectDeadline = projectDeadline;
        this.projectStartDate = projectStartDate;
    }

    public Project(Date projectStartDate, Date projectDeadline){
        this.projectStartDate = projectStartDate;
        this.projectDeadline = projectDeadline;
    }

    public Project(String projectName, Date projectStartDate, Date projectDeadline){
        this.projectStartDate = projectStartDate;
        this.projectDeadline = projectDeadline;
        this.projectName = projectName;
    }

    public void setID(int projectID) {
        this.projectID = projectID;
    }
    public int getID() {
        return projectID;
    }
    public void setName(String projectName) {
        this.projectName = projectName;
    }
    public String getName() {
        return projectName;
    }
    public void setOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }
    public String getOwner() {
        return projectOwner;
    }
    public void setTotalHours(int projectTotalHours) {
        this.projectTotalHours = projectTotalHours;
    }
    public int getTotalHours() {
        return projectTotalHours;
    }
    public void setTotalDays(int projectTotalDays) {
        this.projectTotalDays = projectTotalDays;
    }
    public int getTotalDays() {
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
