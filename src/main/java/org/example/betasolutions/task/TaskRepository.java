package org.example.betasolutions.task;
import org.example.betasolutions.ConnectionManager;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TaskRepository extends PSSTSuperclass {

    @Autowired
    public TaskRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    public int addTask(Task task){
        String sql = "insert into task (task_id,task_name,task_hours,task_days,task_total_price,task_deadline,task_start_date) values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(task,sql);
        try{
            preparedStatement.setInt(1,task.getID());
            preparedStatement.executeUpdate();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public List<ModelInterface> readAllTasks(int ID){
        return super.readAllAssignmentsBelongingToProject("task","task",Task::new,ID);
    }
    public Task readTask(int taskID){
        return (Task) super.readAssingmentByID("task","task",Task::new,taskID);
    }


}



