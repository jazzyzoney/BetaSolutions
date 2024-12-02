package org.example.betasolutions.project;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.FactoryInterface;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.example.betasolutions.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository extends PSSTSuperclass {

    @Autowired
    public ProjectRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    //Create method
    public int insertObjectIntoTable(Project project) {
        String Sql = "insert into project(projectName,projectOwner,projectTotalHours,projectTotalDays,projectTotalPrice,projectDeadline,projectStartDate) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(Sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getOwner());
            preparedStatement.setInt(3, project.getTotalHours());
            preparedStatement.setInt(4, project.getTotalDays());
            preparedStatement.setDouble(5, project.getTotalPrice());
            preparedStatement.setDate(6, project.getDeadline());
            preparedStatement.setDate(7, project.getStartDate());
            preparedStatement.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //Read method
    public List<ModelInterface> readProfile(int projectID) {


        return super.readAllTasks("project", projectID, "project",Project::new);
    }


    public List <Project> readAllProjects(){
        List <Project> projectList = new ArrayList<>();

        for (ModelInterface project : super.readAllTasks("project", "project", Project::new)){ //getting project objects from database
            project.setProductOwner(super.getTableString("project", "projectOwner"));//getting and setting project owner
            projectList.add(project);
        }

        return projectList;
    }




    public int createProject(String projectName, String projectOwner, int hours, int days, double totalPrice) { //hours, days and total price come from Service layer.
        Project project = new Project(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000));
        //86 400 000
        String tableName = "project";

        try {
            conn.setAutoCommit(false);

            int projectID = super.insertObjectIntoTable(project, tableName, projectName, hours, days, totalPrice);

            String sql = "UPDATE project SET project_owner = ? WHERE project_id = ?";
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, projectOwner);
                preparedStatement.setInt(2, projectID);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            conn.commit();
            return projectID;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
