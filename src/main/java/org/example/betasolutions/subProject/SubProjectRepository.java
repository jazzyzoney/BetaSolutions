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
        String sql =( "insert into sub_project (sub_project_name,sub_project_total_hours,sub_project_total_days,sub_project_total_price,sub_project_start_date,sub_project_deadline,project_id) values(?,?,?,?,?,?,?)");
        PreparedStatement preparedStatement = super.insertAssignmentIntoTable(subProject,sql);
        try{
            preparedStatement.setInt(7,subProject.getProjectID());
            preparedStatement.executeUpdate();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //read all subprojects
    public List<SubProject> readAllSubProjects(int projectID){
        List<SubProject> subProjects = new ArrayList<>();
        for(ModelInterface assignmentObject : super.readAllAssignmentsBelongingToProject("sub_project","sub_project","sub_project",SubProject::new,projectID)){
            if(assignmentObject instanceof SubProject){
                SubProject subProject = (SubProject) assignmentObject;
                subProjects.add(subProject);

                int projectIDfromTable = super.getTableIntByInt("sub_project","project_id","sub_project_id",subProject.getID());
                subProject.setProjectID(projectIDfromTable);

                subProjects.add(subProject);
            }
        }
        return subProjects;
    }
    //read a subproject
    public SubProject readSubProject(int subProjectID){
        return (SubProject) super.readAssingmentByID("sub_project","sub_project",SubProject::new,subProjectID);
    }
    //delete a subproject
    public int deleteSubProject(int subProjectID){
        try {
        conn.setAutoCommit(false);
         super.deleteObjectFromTable("sub_project","sub_project",subProjectID);
         super.deleteAllWhere("task","sub_project_id =" + subProjectID);
            conn.commit();
            conn.setAutoCommit(true);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
