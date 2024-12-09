package org.example.betasolutions.subProject;

import org.example.betasolutions.TimeManager;
import org.example.betasolutions.task.Task;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SubProject {

    private List<Task> taskList = new ArrayList<>();

    int subProjectID;
    String subProjectName;
    int subProjectTotalHours;
    int subProjectTotalDays;
    double subProjectTotalPrice;
    Date subProjectStartDate;
    Date subProjectDeadLine;

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
        subProjectDeadLine = deadLine;
    }

    //set all variables:
    public SubProject(String name, int hours, double price, Date startDate, int projectID){
        subProjectName = name;
        subProjectTotalHours = hours;
        subProjectTotalPrice = price;
        subProjectStartDate = startDate;
        this.projectID = projectID;

        TimeManager timeManager = new TimeManager();
        subProjectTotalDays = timeManager.calculateDays(subProjectTotalHours);
        subProjectDeadLine = timeManager.calculateEndDate(subProjectStartDate, subProjectTotalDays);

    }

    public List<Task> getTaskList(){
        return taskList;
    }
}
