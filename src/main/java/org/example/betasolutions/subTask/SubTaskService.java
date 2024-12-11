package org.example.betasolutions.subTask;

import org.example.betasolutions.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {
    private final TaskService taskService;
    private SubTaskRepository subTaskRepository;

    public SubTaskService(SubTaskRepository subTaskRepository, TaskService taskService){
        this.subTaskRepository = subTaskRepository;
        this.taskService = taskService;
    }
    public List<SubTask> readAllSubTasks(int ProjectID){
       return subTaskRepository.readAllSubTasks(ProjectID);
    }

    public void updateSubTaskTotalHours(SubTask subTask, int totalHours){
        subTask.setSubTaskTotalHours(totalHours); //update on object.
        subTaskRepository.updateSubTaskTotalHours(subTask); //update on database

        taskService.updateTaskTotalHours(subTask.getTaskID()); //update on task.

    }
}
