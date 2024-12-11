package org.example.betasolutions.subProject;

import org.example.betasolutions.project.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {
    private final ProjectRepository projectRepository;
    private SubProjectRepository subProjectRepository;

    public SubProjectService(SubProjectRepository subProjectRepository, ProjectRepository projectRepository){
        this.subProjectRepository = subProjectRepository;
        this.projectRepository = projectRepository;
    }
    public void insertIntoSubProject(SubProject subProject){
        subProjectRepository.insertSubProject(subProject);
    }
    public List<SubProject> readAllSubProjects(int projectID){
       return subProjectRepository.readAllSubProjects(projectID);
    }
    public void readSubProjectByID(int subProjectID){
        subProjectRepository.readSubProject(subProjectID);
    }
    public boolean deleteSubProject(int subProjectID){
        return subProjectRepository.deleteSubProject(subProjectID);
    }

    public boolean updateSubProjectTotalHours(int subProjectID){
        SubProject subProject = subProjectRepository.readSubProject(subProjectID);
        int totalHours = subProjectRepository.getTotalHoursForSubProject(subProject);
        subProject.setHours(totalHours);
        subProjectRepository.updateSubProjectTotalHours(subProject, totalHours);

        projectRepository.updateProjectTotalHours(subProject.getProjectID());

    }

}
