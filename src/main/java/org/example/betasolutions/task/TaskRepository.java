package org.example.betasolutions.task;
import org.example.betasolutions.ConnectionManager;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository extends PSSTSuperclass {

    @Autowired
    public TaskRepository(ConnectionManager connectionManager) {
        super(connectionManager);
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

    public boolean addTaskToProject(Task task){
    String sql = "insert into task (task_name, task_hours,task_total_hours,task_days, task_total_days,task_total_price,task_deadline,task_start_date,project_id) values(?,?,?,?,?,?,?,?,?)";
    try (PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        preparedStatement.setString(1, task.getName()); //name
        preparedStatement.setInt(2, task.getHours());   //hours
        preparedStatement.setInt(3, task.getTotalHours());//totalhours
        preparedStatement.setInt(4, task.getDays());//      //days
        preparedStatement.setInt(5, task.getTotalDays());   //totaldays
        preparedStatement.setDouble(6, task.getTotalPrice());  //price
        preparedStatement.setDate(7, task.getDeadline());   //deadline
        preparedStatement.setDate(8, task.getStartDate());  //startDate
        preparedStatement.setInt(9, task.getProjectID());   //project ID
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            return true;
        }
    } catch (Exception e) {
        e.printStackTrace();
        }
        return false;
    }


    public boolean addTaskToSubProject(Task task){
        String sql = "insert into task (task_name, task_hours,task_total_hours,task_days, task_total_days,task_total_price,task_deadline,task_start_date,project_id,sub_project_id) values(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getHours());
            preparedStatement.setInt(3, task.getTotalHours());
            preparedStatement.setInt(4, task.getDays()); //get days
            preparedStatement.setInt(5, task.getTotalDays());
            preparedStatement.setDouble(6, task.getTotalPrice());
            preparedStatement.setDate(7, task.getDeadline());
            preparedStatement.setDate(8, task.getStartDate());
            preparedStatement.setInt(9, task.getProjectID());
            preparedStatement.setInt(10, task.getSubProjectID());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Task> readAllTasksBelongingToProject(int projectID){
        ArrayList<Task> taskList = new ArrayList<>();

        for(ModelInterface assignmentObject : super.readAllAssignmentsBelongingToProject("task","task",Task::new,projectID)) {
            if (assignmentObject instanceof Task) {
                Task task = (Task) assignmentObject; //typecasting.

                int subProjectID = super.getTableIntByInt("task", "sub_project_id", "task_id", task.getID()); //get subprojectID
                int hours = super.getTableIntByInt("task", "task_hours", "task_id", task.getID()); //get hours
                int totalHours = super.getTableIntByInt("task", "task_total_hours", "task_id", task.getID());

                task.setTotalPrice(calculateTotalPriceForTasks(task.getID())); //calculate price


                //get total hours
                task.setHours(hours); //set hours
                task.setTotalHours(totalHours); //set total hours
                task.setSubProjectID(subProjectID); //set subprojectID
                taskList.add(task); //add task to tasklist.
            }
        }
        return taskList;
    }

    public List<Task> readAllTasksForSubProject(int projectID, int subProjectID){

        List <ModelInterface> allTasksOnProject = readAllAssignmentsBelongingToProject("task", //holds tasks temporarily.
                "task",Task::new,projectID);
        List <Task> allTasksForSubProject = new ArrayList<>(); // will be returned.

        for (ModelInterface projectTask : allTasksOnProject){
            if (projectTask instanceof Task){
                Task task = (Task) projectTask; //typecast projecttask as task.

                //set subprojectID and projectID
                task.setProjectID(super.getTableIntByInt("task", "project_id", "task_id", task.getID()));
                task.setSubProjectID(super.getTableIntByInt("task", "sub_project_id", "task_id", task.getID()));


                if (task.getSubProjectID() == subProjectID) {
                    allTasksForSubProject.add(task);//add task to return list.
                }
            }// end of "if (projectTask instanceof Task)"
        }//end of for loop.

        return allTasksForSubProject;
    }

    public Task readTask(int taskID){
        return (Task) super.readAssignmentByID("task","task",Task::new,taskID); //read task using super class.
    }





    public int deleteTask(int taskID){
        try {
        conn.setAutoCommit(false);
            super.deleteObjectFromTable("task", "task", taskID);
            super.deleteAllWhere("sub_task", "task_id = " + taskID);
            super.deleteAllWhere("project_employee_task_subTask", "task_id = " + taskID);
            super.deleteAllWhere("project_employee_task", "task_id = " + taskID);
            conn.commit();
            conn.setAutoCommit(true);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public double calculateTotalPriceForTasks(int taskID) {
        double totalPrice = 0;
        totalPrice += super.CalculatePrice(taskID, "sub_task");
        totalPrice += super.CalculatePrice(taskID, "task");

        String SQL = "update task set task_total_price = ? where task_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setDouble(1, totalPrice);
            preparedStatement.setInt(2, taskID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
    }
        return totalPrice;
    }


}



