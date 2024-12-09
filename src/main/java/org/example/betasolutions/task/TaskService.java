package org.example.betasolutions.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    public void createTaskForProejct(Task task){
        taskRepository.addTaskToProject(task);
    }
    public void createTaskForSubProject(Task task){
        taskRepository.addTaskToSubProject(task);
    }

    public List<Task> getAllTasksBelongingToProject(int projectID){
        return taskRepository.readAllTasksBelongingToProject(projectID);
    }

    public List <Task> getAllTasksForToSubProject(int subProjectID){
        return taskRepository.readAllTasksForSubProject(subProjectID);
    }
    public boolean deleteTask(int taskID){
        return taskRepository.deleteTask(taskID);
    }

}
