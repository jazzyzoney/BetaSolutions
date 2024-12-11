package org.example.betasolutions.task;

import org.example.betasolutions.TimeManager;

import org.example.betasolutions.project.ProjectService;
import org.example.betasolutions.subProject.SubProjectRepository;
import org.example.betasolutions.subProject.SubProjectService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    private SubProjectService subProjectService;
    private final ProjectService projectService;
    private TimeManager timeManager;

    public TaskService(TaskRepository taskRepository, SubProjectService subProjectService, ProjectService projectService){
        this.taskRepository = taskRepository;
        this.subProjectService = subProjectService;

        timeManager = new TimeManager();
        this.projectService = projectService;
    }

    public void createTaskForProject(Task task){
        task.setTotalHours(getTotalHoursForTask(task));//set totalHours, totalDays, and deadline.
        taskRepository.addTaskToProject(task);
    }


    public void createTaskForSubProject(Task task){
        task.setTotalHours(getTotalHoursForTask(task));//set totalHours, totalDays, and deadline.
        taskRepository.addTaskToSubProject(task);
    }

    public int getTotalHoursForTask(Task task){
        return taskRepository.getTotalHoursForTask(task);
    }

    public List<Task> getAllTasksBelongingToProject(int projectID){
        return taskRepository.readAllTasksBelongingToProject(projectID);
    }

    public List <Task> getAllTasksForSubProject(int projectID, int subProjectID){
        return taskRepository.readAllTasksForSubProject(projectID, subProjectID);
    }
    public boolean deleteTask(int taskID){
        return taskRepository.deleteTask(taskID);

    }

    public Task getTask(int taskID){
        Task task = (Task) taskRepository.readTask(taskID);//readAllAssignmentsBelongingToProject("task", "task", Task::new, projectID).get(taskID - 1);
        return task;
    }

    /*
    public void updateHours(Task task, int hours) {
        //task.setTotalHours(hours); //set task hours = new hours;

        int taskID = task.getID();
        int days = timeManager.calculateDays(hours); //calculate days;
        //task.setTotalDays(days);  //set task days.

        //System.out.println("taskservice. start date: " + task.getStartDate());

        Date deadline = timeManager.calculateEndDate(task.getStartDate(), days); //calculate new expected end date for task, using startdate and days.


        taskRepository.updateTaskTotalHours(taskID, hours);
        taskRepository.updateTaskTotalDays(taskID, days);
        taskRepository.updateTaskDeadline(taskID, deadline);

    }
*/

    public boolean updateTaskTotalHours(int taskID){
        Task task = taskRepository.readTask(taskID); //read task.
        int totalHours = taskRepository.getTotalHoursForTask(task); //getTotalHours for task.
        task.setTotalHours(totalHours);//set hours on task.
        taskRepository.updateTaskTotalHours (task, totalHours); //update task total hours on database.

        if (task.getSubProjectID() > 0) {
            subProjectService.updateSubProjectTotalHours(task.getSubProjectID());
        }else{
            projectService.updateProjectTotalHours(task.getProjectID());
        }
    }
}
