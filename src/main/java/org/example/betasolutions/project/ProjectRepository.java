package org.example.betasolutions.project;

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
            preparedStatement.setInt(3, project.getHours());
            preparedStatement.setInt(4, project.getDays());
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

    //read all method for projects
    //do we need a way to only read projects that a team leader is assinged to ?
    public List<Project> readAllProjects() {
        List<Project> allProjects = new ArrayList<>();
        String sql = "select * from project";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeQuery();
            while (preparedStatement.getResultSet().next()) {
                int id = preparedStatement.getResultSet().getInt("projectID");
                String name = preparedStatement.getResultSet().getString("projectName");
                String owner = preparedStatement.getResultSet().getString("projectOwner");
                int hours = preparedStatement.getResultSet().getInt("projectTotalHours");
                int days = preparedStatement.getResultSet().getInt("projectTotalDays");
                double totalPrice = preparedStatement.getResultSet().getDouble("projectTotalPrice");
                Date deadline = preparedStatement.getResultSet().getDate("projectDeadline");
                Date startDate = preparedStatement.getResultSet().getDate("projectStartDate");
                Project project = new Project(id, name, owner, hours, days, totalPrice, deadline, startDate);
                allProjects.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allProjects;
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
