package org.example.betasolutions.task;
import org.example.betasolutions.ConnectionManager;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository extends PSSTSuperclass {

    public TaskRepository(ConnectionManager connectionManager) {
        super(connectionManager);

    }



    public List<Task> getAllTasks(int projectId) {
        List<ModelInterface> modelInterfaces = super.readAllTasks("task", projectId, "task", Task::new);
        List<Task> tasks = new ArrayList<>();
        for (ModelInterface modelInterface : modelInterfaces) {
            tasks.add((Task) modelInterface);
            tasks.forEach(System.out::println);
        }
        return tasks;
    }
}
