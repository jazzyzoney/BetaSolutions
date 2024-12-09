package org.example.betasolutions.project;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.TimeManager;
import org.example.betasolutions.employee.Employee;
import org.example.betasolutions.subProject.SubProject;
import org.example.betasolutions.task.Task;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Project implements ModelInterface {
    private List<SubProject> subProjectList = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    private int projectID;
    private String projectName;
    private String projectOwner;
    private int projectTotalHours;
    private int projectTotalDays;
    private double projectTotalPrice;
    private Date projectDeadline;
    private Date projectStartDate;

    //empty
    public Project(){}

    //factory interface
    public Project(int id, String name, int hours, int days, double totalPrice, Date Deadline, Date startDate) {
        this.projectID = id;
        this.projectName = name;
        this.projectTotalHours = hours;
        this.projectTotalDays = days;
        this.projectTotalPrice = totalPrice;
        this.projectDeadline = Deadline;
        this.projectStartDate = startDate;
    }

    //set all variables.
    public Project( String projectName, String projectOwner, int projectTotalHours, double projectTotalPrice, Date projectStartDate) {
        //this.projectID = projectID;
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.projectTotalHours = projectTotalHours;
        this.projectTotalPrice = projectTotalPrice;
        this.projectStartDate = projectStartDate;

        TimeManager timeManager = new TimeManager();
        projectTotalDays = timeManager.calculateDays(projectTotalHours);
        projectDeadline = timeManager.calculateEndDate(projectStartDate, projectTotalDays);
    }

    /*

    //id, name, po, hours, days, price, startdate, deadline
    public Project(int projectID, String projectName, String projectOwner, int projectTotalHours, int projectTotalDays, double projectTotalPrice, Date projectStartDate, Date projectDeadline) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.projectTotalHours = projectTotalHours;
        this.projectTotalDays = projectTotalDays;
        this.projectTotalPrice = projectTotalPrice;
        this.projectDeadline = projectDeadline;
        this.projectStartDate = projectStartDate;
    }

    //name, po, hours, days, price, startdate, deadline.
    public Project(String projectName, String projectOwner, int projectTotalHours, int projectTotalDays, double projectTotalPrice, Date projectDeadline, Date projectStartDate) {
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.projectTotalHours = projectTotalHours;
        this.projectTotalDays = projectTotalDays;
        this.projectTotalPrice = projectTotalPrice;
        this.projectDeadline = projectDeadline;
        this.projectStartDate = projectStartDate;
    }


    public Project(Date projectStartDate, Date projectDeadline) {
        this.projectStartDate = projectStartDate;
        this.projectDeadline = projectDeadline;
    }

    public Project(String projectName, Date projectStartDate, Date projectDeadline) {
        this.projectStartDate = projectStartDate;
        this.projectDeadline = projectDeadline;
        this.projectName = projectName;
    }

*/
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

    public int getHours() {
        return projectTotalHours;
    }

    public void setTotalDays(int projectTotalDays) {
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

    public int getTotalHours() {
        return projectTotalHours;
    }

    public int getTotalDays() {
        return projectTotalDays;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public List<SubProject> getSubProjectList() {
        return subProjectList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

}





