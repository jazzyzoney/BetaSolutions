package org.example.betasolutions.project;

import org.example.betasolutions.FactoryInterface;
import org.example.betasolutions.ModelInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public void insertAssignmentIntoTable(Project project){
        //updateProjectTotalHours(project.getID());
        projectRepository.insertAssignmentIntoTable(project);
    }
    public List<Project> readAllProjects(){
       return projectRepository.readAllProjects();
    }
    public Project readProjectByID(int project_id){
        return projectRepository.readProjectByID(project_id);
    }
    public void updateProject(Project project,int project_id){
        projectRepository.updateProject(project,project_id);
    }

    public void updateProjectTotalHours(int projectID){
        Project project = projectRepository.readProjectByID(projectID); //read project.
        int totalHours = projectRepository.getTotalHoursForProject(project);//get total hours
        project.setTotalHours(totalHours); //update object.
        projectRepository.updateTotalHoursForProject(projectID, totalHours); //update database.
    }


}
