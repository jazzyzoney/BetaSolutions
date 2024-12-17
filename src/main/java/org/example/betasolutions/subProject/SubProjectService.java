package org.example.betasolutions.subProject;

import org.example.betasolutions.project.ProjectService;
import org.example.betasolutions.TimeManager;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {
    private final ProjectService projectService;
    private SubProjectRepository subProjectRepository;
    private TimeManager timeManager;

    public SubProjectService(SubProjectRepository subProjectRepository, ProjectService projectService){
        this.subProjectRepository = subProjectRepository;
        this.projectService = projectService;
        timeManager = new TimeManager();
    }

    public void insertIntoSubProject(SubProject subProject){
        //updateSubProjectTotalHours(subProject.getID(), 0);
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

    public void updateSubProjectTotalHours(int subProjectID){
        SubProject subProject = subProjectRepository.readSubProject(subProjectID); //read subproject.
        int totalHours = subProjectRepository.getTotalHoursForSubProject(subProject); //get totalHours.

        subProject.setHours(totalHours);//total hours.
        calculateDeadline(subProject);//update on object.

        subProjectRepository.updateSubProjectTotalHours(subProject, totalHours); //update on database.

        projectService.updateProjectTotalHours(subProject.getProjectID()); //update project.
    }

    public void calculateDeadline(SubProject subProject){
        subProject.setDays(timeManager.calculateDays(subProject.getHours()));
        subProject.setDeadline(timeManager.calculateEndDate(subProject.getStartDate(), subProject.getDays()));
    }
}
