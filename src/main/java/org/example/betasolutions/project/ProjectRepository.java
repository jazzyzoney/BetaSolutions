package org.example.betasolutions.project;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.FactoryInterface;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.example.betasolutions.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class ProjectRepository extends PSSTSuperclass {

    @Autowired
    public ProjectRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    //Create method
    public int insertAssignmentIntoTable(Project project) {
       /* String Sql = "insert into project(projectName,projectOwner,projectTotalHours,projectTotalDays,projectTotalPrice,projectDeadline,projectStartDate) values(?,?,?,?,?,?,?)";
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
        }*/
        String sql = "INSERT INTO project (projectName, projectTotalHours, projectTotalDays, projectTotalPrice, projectDeadline, projectStartDate) VALUES (?,?,?,?,?,?,?)";

                //"project", "?,?,?,?,?,?,?"

        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(project, sql);
        try{
            preparedStatement.setString(7,project.getProjectOwner());//set projectowner.

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);//return objectID.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;//if failed
    }

    //Read method
    public List<ModelInterface> readProfile(int projectID) {


        return super.readAllTasks("project", projectID, "project",Project::new);
    }




    public int createProject(String projectName, String projectOwner, int hours, int days, double totalPrice) { //hours, days and total price come from Service layer.
        Project project = new Project(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000));
        //86 400 000
        String tableName = "project";

        try {
            conn.setAutoCommit(false);

            int projectID = insertAssignmentIntoTable(project);

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
