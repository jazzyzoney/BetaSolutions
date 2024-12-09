package org.example.betasolutions.task;
import org.example.betasolutions.ConnectionManager;

import org.example.betasolutions.PSSTSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

@Repository
public class TaskRepository extends PSSTSuperclass {

    @Autowired
    public TaskRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<Task> getAllTasksBelongingToProject(int projectID){
        List <Task> allTasks = super.readAllAssignmentsBelongingToProject("task", "task", Task::new, projectID);
        for (Task task : allTasks){
            task.setSubprojectID(getTableIntByInt("task", "subproject_id", "project_id", projectID));
            task.setProjectID(projectID);
        }
        return allTasks;
    }

    public void updateTaskHours(int taskID, int taskHours){
        super.updateInt("task", "task_Total_Hours", taskID, taskHours);
    }

    public void updateTaskDays(int taskID, int taskDays){
        super.updateInt("task", "task_total_days", taskID, taskDays);

    }

    public void updateTaskDeadline(int taskID, Date taskDate){
        super.updateDate("task", "task_deadline", taskID, taskDate);
    }
}



