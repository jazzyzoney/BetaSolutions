package org.example.betasolutions.project;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

@Repository
public class ProjectRepository extends PSSTSuperclass {

    @Autowired
    public ProjectRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    public int createProject(String projectName, String projectOwner, int hours, int days, double totalPrice) { //hours, days and total price come from Service layer.
        Project project = new Project(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+ 86400000));
        //86 400 000
        String tableName = "project";
        int projectID = super.insertObjectIntoTable(project,tableName,projectName,hours,days,totalPrice);

        String sql = "UPDATE project SET projectOwner = ? WHERE projectID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,projectOwner);
            preparedStatement.setInt(2,projectID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectID;
    }
}
