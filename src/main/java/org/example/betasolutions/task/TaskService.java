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
        taskRepository.addTaskForProject(task);
    }
    public void createTaskForSubProject(Task task){
        taskRepository.addTaskToSubProject(task);
    }

    public List<Task> getAllTasks(int projectID){
        return taskRepository.readAllTasks(projectID);
    }
    public boolean deleteTask(int taskID){
        return taskRepository.deleteTask(taskID);
    }

}
