package org.example.betasolutions.task;
import org.example.betasolutions.ConnectionManager;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository extends PSSTSuperclass {

    @Autowired
    public TaskRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    public int addTaskForProject(Task task){
        String sql = "insert into task (task_name, task_total_hours,task_total_days,task_total_price,task_deadline,task_start_date,project_id) values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(task,sql);
        try{
            preparedStatement.setInt(7,task.getProjectID());
            preparedStatement.executeUpdate();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public int addTaskToSubProject(Task task){
        String sql = "insert into task (task_name, task_total_hours,task_total_days,task_total_price,task_deadline,task_start_date,project_id, sub_project_id) values(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(task,sql);
        try{
            preparedStatement.setInt(7,task.getProjectID());
            preparedStatement.setInt(8,task.getSubProjectID());
            preparedStatement.executeUpdate();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    public List<Task> readAllTasks(int ID){
        ArrayList<Task> taskList = new ArrayList<>();

        for(ModelInterface assignmentObject : super.readAllAssignmentsBelongingToProject("task","task","task",Task::new,ID)) {
            if (assignmentObject instanceof Task) {
                Task task = (Task) assignmentObject;

                int subProjectID = super.getTableIntByInt("task", "sub_project_id", "task_id", task.getID());
                task.setSubProjectID(subProjectID);
                taskList.add(task);
            }
        }
        return taskList;
    }

    public List<ModelInterface> readAllTasksForSubProject(int ID){
        return super.readAllAssignmentsBelongingToProject("task","task","task",Task::new,ID);
    }

    public Task readTask(int taskID){
        return (Task) super.readAssingmentByID("task","task",Task::new,taskID);
    }
    public int deleteTask(int taskID){
        if (super.deleteObjectFromTable("task", "task", taskID)){
            return 1;
        }
        return 0;

    }


}



