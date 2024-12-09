package org.example.betasolutions.subTask;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubTaskRepository extends PSSTSuperclass {

    public SubTaskRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    public void addSubTaskToTask(SubTask subTask){
        String sql = "insert into sub_task (sub_task_name, sub_task_total_hours,sub_task_total_days,sub_task_total_price,sub_task_deadline,sub_task_start_date,task_id) values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(subTask,sql);
        try{
            preparedStatement.setInt(7,subTask.getTaskID());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<SubTask> readAllSubTasks(int ProjectID){
        ArrayList<SubTask> subTaskList = new ArrayList<>();
        String SQL ="SELECT *  FROM sub_task JOIN task ON sub_task.task_id = task.task_id WHERE task.project_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1,ProjectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                SubTask subTask = new SubTask();
                subTask.setSubTaskID(resultSet.getInt("sub_task_id"));
                subTask.setSubTaskName(resultSet.getString("sub_task_name"));
                subTask.setSubTaskTotalHours(resultSet.getInt("sub_task_total_hours"));
                subTask.setSubTaskTotalDays(resultSet.getInt("sub_task_total_days"));
                subTask.setSubTaskTotalPrice(resultSet.getDouble("sub_task_total_price"));
                subTask.setSubTaskDeadline(resultSet.getDate("sub_task_deadline"));
                subTask.setSubTaskStartDate(resultSet.getDate("sub_task_start_date"));
                subTask.setTaskID(resultSet.getInt("task_id"));
                subTaskList.add(subTask);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return subTaskList;
    }
}
