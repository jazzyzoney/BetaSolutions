package org.example.betasolutions.project;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository extends PSSTSuperclass {

    @Autowired
    public ProjectRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    //Create method
    // Insert project into project table with the projectOwner.which is still completely nuts to me.
    public int insertAssignmentIntoTable(Project project) {
        String sql = "INSERT INTO project (projectName, projectTotalHours, projectTotalDays, projectTotalPrice, projectDeadline, projectStartDate, projectOwner) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(project, sql);
        try{
            preparedStatement.setString(7,project.getProjectOwner());//set projectowner.
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt("projectID");//return objectID.
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

        for (ModelInterface assignmentObject : super.readAllAssignments("project", "project", Project::new)){
            //typecasting assignmentObject as Project:
            if (assignmentObject instanceof Project){
                Project project = (Project) assignmentObject;

                //Getting projectowner from projectTAble using projectID.
                String projectOwner = super.getTableStringByInt("project", "projectOwner",
                        "projectID", project.getID());

                //adding projectowner to projectObject.
                project.setProjectOwner(projectOwner);
                //adding projectObject to projectList.
                projectList.add(project);
            }
        }//end of for loop.
        return projectList;
    }
    //Read method by ID for project. not sure if this actually works but i will test it later.
    public Project readProjectByID(int projectID){
        Project Project = (Project) super.readAssingmentByID("project", "project",Project::new, projectID);
        if (Project != null){
            String projectOwner = super.getTableStringByInt("project", "projectOwner", "projectID", Project.getID());
            Project.setProjectOwner(projectOwner);
        }
        return Project;
    }
}
