package org.example.betasolutions.project;

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

    //Read method
    //this is just a basic read method for all projects in the project table.i have a idea on how to make it work with superclass read method instead but i need to test it first so this is a placeholder for now.
    public List<Project> ReadAllProjects(int active) {
        ArrayList<Project> allProjects = new ArrayList<>();
        String sql = "select * from project where active = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("projectID");
                String name = resultSet.getString("projectName");
                int hours = resultSet.getInt("projectTotalHours");
                int days = resultSet.getInt("projectTotalDays");
                double totalPrice = resultSet.getInt("projectTotalPrice");
                String projectOwner = resultSet.getString("projectOwner");
                Date endDate = resultSet.getDate("projectDeadline");
                Date startDate = resultSet.getDate("projectStartDate");
                allProjects.add(new Project(id, name, projectOwner, hours, days, totalPrice, startDate, endDate));
            }
            return allProjects;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
