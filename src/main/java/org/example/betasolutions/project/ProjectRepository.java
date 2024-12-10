package org.example.betasolutions.project;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.example.betasolutions.subProject.SubProject;
import org.example.betasolutions.subProject.SubProjectRepository;
import org.example.betasolutions.subTask.SubTask;
import org.example.betasolutions.subTask.SubTaskRepository;
import org.example.betasolutions.task.Task;
import org.example.betasolutions.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class ProjectRepository extends PSSTSuperclass {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SubTaskRepository subTaskRepository;
    @Autowired
    private SubProjectRepository subProjectRepository;

    @Autowired
    public ProjectRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    //Create method
    // Insert project into project table with the projectOwner.which is still completely nuts to me.
    public int insertAssignmentIntoTable(Project project) {
        String sql = "INSERT INTO project (project_Name, project_Total_Hours, project_Total_Days, project_Total_Price, project_Deadline, project_Start_Date, project_Owner) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(project, sql);
        try {
            preparedStatement.setString(7, project.getProjectOwner());//set projectowner.
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt("project_ID");//return objectID.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;//if failed
    }
    //update


    //Read method
    //this is just a basic read method for all projects in the project table.i have a idea on how to make it work with superclass read method instead but i need to test it first so this is a placeholder for now.
    public List<Project> readAllProjects() {
        ArrayList<Project> projectList = new ArrayList<>();

        for (ModelInterface assignmentObject : super.readAllAssignments("project", "project", Project::new)) {
            //typecasting assignmentObject as Project:
            if (assignmentObject instanceof Project) {
                Project project = (Project) assignmentObject;

                //Getting projectowner from projectTAble using projectID.
                String projectOwner = super.getTableStringByInt("project", "project_Owner", "project_ID", project.getID());

                //adding projectowner to projectObject.
                project.setProjectOwner(projectOwner);
                //adding projectObject to projectList.
                projectList.add(project);
            }
        }//end of for loop.
        return projectList;
    }

    //Read method by ID for project. not sure if this actually works but i will test it later.
    public Project readProjectByID(int projectID) {
        Project Project = (Project) super.readAssignmentByID("project", "project", Project::new, projectID);
        if (Project != null) {
            String projectOwner = super.getTableStringByInt("project", "project_Owner", "project_ID", Project.getID());
            Project.setProjectOwner(projectOwner);
        }
        return Project;
    }

    public boolean updateProject(Project project, int projectID) {
        String sql = "UPDATE project SET project_Name = ?, project_Owner = ?, project_Total_Hours = ?, project_Total_Days = ?, project_Total_Price = ?, project_Deadline = ?,project_Start_Date =? WHERE project_ID = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getProjectOwner());
            preparedStatement.setInt(3, project.getTotalHours());
            preparedStatement.setInt(4, project.getTotalDays());
            preparedStatement.setDouble(5, project.getTotalPrice());
            preparedStatement.setDate(6, project.getDeadline());
            preparedStatement.setDate(7, project.getStartDate());
            preparedStatement.setInt(8, projectID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SubProject> getAllSubProjects(int projectID){
        List <SubProject> subProjects = subProjectRepository.readAllSubProjects(projectID); //get all subprojects using subprojectRepository.
        return subProjects;
    }

    public List<Task> getAllTasksForSubProject (int projectID, int subProjectID){
        List <Task> tasks = taskRepository.readAllTasksForSubProject(projectID, subProjectID); //get all tasks for subproject, using taskRepository.
        return tasks;
    }

    public List <Task> getAllTasksBelongingToProject(int projectID){
        List <Task> tasks = taskRepository.readAllTasksBelongingToProject(projectID);
        return tasks;
    }

    public List <SubTask> getAllSubTasks (int taskID, int projectID){
        List <SubTask> subTasks = subTaskRepository.readSubTaskBelongingToTask(taskID, projectID);
        return subTasks;
    }
    /*
    
    public void blalbla() {
        //the maps contain a type of assignment and the assignments below.
        //eg. Subproject and lists of tasks.
        Map<SubProject, List<Task>> subProjectsAndTasks = new HashMap<>(); //all tasks belonging to subprojects.
        Map<Task, List<SubTask>> tasksAndSubTasks = new HashMap<>(); //all subtasks in tasks
        List<Task> tasksWithoutSubProject = new ArrayList<>(); //all tasks, indpendent of subprojects.


        //for each subproject in project, add to Map, and create empty arraylist.
        for (SubProject subProject : subProjects) {
            subProjectsAndTasks.put(subProject, new ArrayList<>());
        }

        //For each task in project :
        for (Task task : tasks) {
            if (task.getSubProjectID() != 0) { //if task has subproject:

                for (SubProject subProject : subProjects) { //for each subproject.
                    if (task.getSubProjectID() == subProject.getID()) { //if task.subprojectID and subproject.subprojectID are equal:
                        subProjectsAndTasks.get(subProject).add(task); //add task to subproject.
                        break; // no need to look at rest of subprojects.
                    }
                }
            } else { //if task doesn't have a subproject
                tasksWithoutSubProject.add(task); //add subproject
            } //end of 'if task.getSubProjectID() != 0)'
        }//end of outer loop.

        for (Task task : tasks) {// for each task in project
            tasksAndSubTasks.put(task, new ArrayList<>()); //add task and new arraylist to hashmap.
        }

        for (Task task : tasks) { //for each task in project
            for (SubTask subTask : subTasks) { //for each subtask in project.
                if (subTask.getTaskID() == task.getID()) { //if subtask.taskID and task.taskID are equal
                    tasksAndSubTasks.get(task).add(subTask); //add subtask to subtasklist in task.
                    break; //no need to look at rest of subtasks.
                }//end of 'if (subTask.getTaskID() == task.getID())'
            }//end of inner for loop (subtask)
        }//end of outer for loop (task)
    }*/

}
