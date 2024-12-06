package org.example.betasolutions.task;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.TimeCalculator;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private TimeCalculator timeCalculator;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        timeCalculator = new TimeCalculator();
    }

    public List<ModelInterface> getAllTasks(int projectID){
        return taskRepository.readAllAssignmentsBelongingToProject("task","task",Task::new, projectID);
    }

    public Task getTask(int projectID, int taskID){
        Task task = (Task) taskRepository.readAllAssignmentsBelongingToProject("task", "task", Task::new, projectID).get(taskID - 1);
        return task;
    }

    public void updateHours(Task task, int hours) {
        //task.setTotalHours(hours); //set task hours = new hours;

        int taskID = task.getID();
        int days = timeCalculator.calculateDays(hours); //calculate days;
        //task.setTotalDays(days);  //set task days.

        //System.out.println("taskservice. start date: " + task.getStartDate());

        Date deadline = timeCalculator.calculateEndDate(task.getStartDate(), days); //calculate new expected end date for task, using startdate and days.


        taskRepository.updateTaskHours(taskID, hours);
        taskRepository.updateTaskDays(taskID, days);
        taskRepository.updateTaskDeadline(taskID, deadline);

    }

}
