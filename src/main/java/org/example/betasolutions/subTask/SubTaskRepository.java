package org.example.betasolutions.subTask;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class SubTaskRepository extends PSSTSuperclass {

    public SubTaskRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    public void addSubTaskForTask(SubTask subTask){
        String sql = "insert into sub_task (sub_task_name, sub_task_total_hours,sub_task_total_days,sub_task_total_price,sub_task_deadline,sub_task_start_date,task_id) values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(subTask,sql);
        try{
            preparedStatement.setInt(7,subTask.getTaskID());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
