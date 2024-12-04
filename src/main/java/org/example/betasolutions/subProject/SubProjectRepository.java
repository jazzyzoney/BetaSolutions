package org.example.betasolutions.subProject;
import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubProjectRepository extends PSSTSuperclass {

    public SubProjectRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
    //create a subproject
    public int insertIntoSubProject(SubProject subProject){
        String sql =( "insert into subproject (sub_project_id,sub_project_name,sub_project_total_hours,sub_project_total_days,sub_project_total_price,sub_project_start_date,sub_project_deadline,project_id) values(?,?,?,?,?,?,?,?)");
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(subProject,sql);
        try{
            preparedStatement.setInt(8,subProject.getID());
            preparedStatement.executeUpdate();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //read all subprojects
    public List<ModelInterface> readAllSubProjects(int projectID){
        return super.readAllAssignmentsBelongingToProject("sub_project","sub_project",SubProject::new,projectID);
    }
    //read a subproject
    public SubProject readSubProject(int subProjectID){
        return (SubProject) super.getTableStringByInt("sub_project","sub_project_id","sub_project",);
    }
}
