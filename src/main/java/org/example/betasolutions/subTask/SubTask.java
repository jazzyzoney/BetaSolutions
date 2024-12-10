package org.example.betasolutions.subTask;

import org.example.betasolutions.TimeManager;

import java.sql.Date;

public class SubTask {

    int subTaskID;
    String subTaskName;
    int subTaskTotalHours;
    int subTaskTotalDays;
    double subTaskTotalPrice;
    Date subTaskStartDate;
    Date subTaskDeadline;

    int taskID;

    public SubTask(){}

    public SubTask(int id, String name, int hours, int days, Date startDate, Date deadline){
        subTaskID = id;
        subTaskName = name;
        subTaskTotalHours = hours;
        subTaskTotalDays = days;
        subTaskStartDate = startDate;
        subTaskDeadline = deadline;
    }

    public SubTask(String name, int hours, int days, Date startDate, Date deadline, int taskID){
        subTaskName = name;
        subTaskStartDate = startDate;
        this.taskID = taskID;

        setHours(hours);

    }

    public void setHours(int hours){
        subTaskTotalHours = hours;

        TimeManager timeManager = new TimeManager();

        subTaskTotalDays = timeManager.calculateDays(subTaskTotalHours);
        subTaskDeadline = timeManager.calculateEndDate(subTaskStartDate, subTaskTotalDays);
    }
}
