package org.example.betasolutions.project;

import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class ProjectRepository extends PSSTSuperclass {

    public ProjectRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    public int createProject(Project project, String tableName, String name, int hours, int days, int totalPrice) {
        int projectID = super.create(project,tableName,name,hours,days,totalPrice);

        String sql = "UPDATE project set projectOwner = ? where projectID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,project.getOwner());
            preparedStatement.setInt(2,projectID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectID;
    }
}
