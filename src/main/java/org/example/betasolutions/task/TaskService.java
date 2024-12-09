package org.example.betasolutions.task;

import org.example.betasolutions.TimeManager;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private TimeManager timeManager;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        timeManager = new TimeManager();
    }

    public void createTaskForProject(Task task){
        taskRepository.addTaskForProject(task);
    }

    public void createTaskForSubProject(Task task){
        taskRepository.addTaskToSubProject(task);
    }


    public List<Task> getAllTasks(int projectID){
        return taskRepository.readAllTasks(projectID);
    }

    public Task getTask(int taskID){
        Task task = (Task) taskRepository.readTaskByID(taskID);//readAllAssignmentsBelongingToProject("task", "task", Task::new, projectID).get(taskID - 1);
        return task;
    }

    public void updateHours(Task task, int hours) {
        //task.setTotalHours(hours); //set task hours = new hours;

        int taskID = task.getID();
        int days = timeManager.calculateDays(hours); //calculate days;
        //task.setTotalDays(days);  //set task days.

        //System.out.println("taskservice. start date: " + task.getStartDate());

        Date deadline = timeManager.calculateEndDate(task.getStartDate(), days); //calculate new expected end date for task, using startdate and days.


        taskRepository.updateTaskHours(taskID, hours);
        taskRepository.updateTaskDays(taskID, days);
        taskRepository.updateTaskDeadline(taskID, deadline);

    }

}
