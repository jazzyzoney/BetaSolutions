package org.example.betasolutions.task;
import org.example.betasolutions.ConnectionManager;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository extends PSSTSuperclass {

    @Autowired
    public TaskRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
/*<<<<<<< HEAD

    public List<Task> getAllTasksBelongingToProject(int projectID){
        List <Task> allTasks = new ArrayList<>();

        List <ModelInterface> tempList = super.readAllAssignmentsBelongingToProject("task", "task", Task::new, projectID); //temp list.
        for (ModelInterface modelInterface : tempList){
            if (modelInterface instanceof Task) {
                Task task = (Task) modelInterface; //typecasting modelinterface as Task.
                task.setSubProjectID(getTableIntByInt("task", "subproject_id", "project_id", projectID)); //set subprojectID.
                task.setProjectID(projectID); //set projectID.

                allTasks.add(task); //add task to taskList.
            }
        }

        return allTasks; //return all tasks.
    }*/



    public void updateTaskHours(int taskID, int taskHours){
        super.updateInt("task", "task_Total_Hours", taskID, taskHours);
    }

    public void updateTaskDays(int taskID, int taskDays){
        super.updateInt("task", "task_total_days", taskID, taskDays);

    }

    public void updateTaskDeadline(int taskID, Date taskDate){
        super.updateDate("task", "task_deadline", taskID, taskDate);
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

    

//>>>>>>> master
}



