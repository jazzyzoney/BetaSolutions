package org.example.betasolutions.task;

import org.example.betasolutions.ModelInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<ModelInterface> getAllTasks(int projectID){
        return taskRepository.readAllTasks(projectID);
    }

}
