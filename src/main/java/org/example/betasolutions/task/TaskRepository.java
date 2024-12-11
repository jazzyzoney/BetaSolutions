package org.example.betasolutions.task;
import org.example.betasolutions.ConnectionManager;

import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.example.betasolutions.subTask.SubTask;
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

    public void updateTaskTotalHours(int taskID, int taskHours){
        super.updateInt("task", "task_Total_Hours", taskID, taskHours);
    }

    public void updateTaskTotalDays(int taskID, int taskDays){
        super.updateInt("task", "task_total_days", taskID, taskDays);

    }

    public void updateTaskDeadline(int taskID, Date taskDate){
        super.updateDate("task", "task_deadline", taskID, taskDate);
    }

    public boolean addTaskToProject(Task task){
        String sql = "insert into task (task_name, task_total_hours,task_total_days,task_total_price,task_deadline,task_start_date,project_id) values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(task,sql); //get prepared statement from superclass.
        try{
            preparedStatement.setInt(7,task.getProjectID()); //set project id for task.

            preparedStatement.setInt(2, getTotalHoursForTask(task));//set total hours for task.
            preparedStatement.executeUpdate(); //add task to database.
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean addTaskToSubProject(Task task){
        String sql = "insert into task (task_name, task_total_hours,task_total_days,task_total_price,task_deadline,task_start_date,project_id, sub_project_id) values(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(task,sql); //get prepared statement from superclass.
        try{
            preparedStatement.setInt(7,task.getProjectID()); //set project id for task.
            preparedStatement.setInt(8,task.getSubProjectID()); //set subproject id for task.
            preparedStatement.executeUpdate(); //add task to database.
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public List<Task> readAllTasksBelongingToProject(int projectID){
        ArrayList<Task> taskList = new ArrayList<>();

        for(ModelInterface assignmentObject : super.readAllAssignmentsBelongingToProject("task","task",Task::new,projectID)) {
            if (assignmentObject instanceof Task) {
                Task task = (Task) assignmentObject; //typecasting.

                int subProjectID = super.getTableIntByInt("task", //get subprojectID.
                        "sub_project_id","task_id", task.getID());

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

    public boolean deleteTask(int taskID){
        if (super.deleteObjectFromTable("task", "task", taskID)){
            return true;
        }
        return false;
    }

    public int getTotalHoursForTask(Task task){
        int totalHoursForTask = task.getHours(); //get task-specific hours.

        List<ModelInterface> allSubTasks = super.readAllAssignments("sub_task", "sub_task", SubTask::new);//get All subtasks.

        for (ModelInterface modelInterface : allSubTasks){
            SubTask subTask = (SubTask) modelInterface; //typecasting.
            if (subTask.getTaskID() == task.getID()){
                totalHoursForTask += subTask.getHours(); //add subtask-specific hours to total.
            }
        }//end of all subtasks.

        return totalHoursForTask;
    }

    public boolean updateTotalHoursForTask(int taskID, int newTotalHoursForTask){
        return super.updateInt("task", "task_total_hours", taskID, newTotalHoursForTask);
    }

    public boolean updateHoursForTask(Task task, int newHoursForTask){
        task.setHours(newHoursForTask); //set Task hours.
        int totalHours = getTotalHoursForTask(task); //get new total hours.
        
        return updateTotalHoursForTask(task.getID(), totalHours); //update total hours for task.
    }

}



