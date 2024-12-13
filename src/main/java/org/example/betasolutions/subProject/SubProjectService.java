package org.example.betasolutions.subProject;

import org.example.betasolutions.TimeManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {
    private SubProjectRepository subProjectRepository;
    private TimeManager timeManager;

    public SubProjectService(SubProjectRepository subProjectRepository){
        this.subProjectRepository = subProjectRepository;
        timeManager = new TimeManager();
    }

    public void insertIntoSubProject(SubProject subProject){
        calculateDeadline(subProject);
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

    public void calculateDeadline(SubProject subProject){
        subProject.setDays(timeManager.calculateDays(subProject.getHours()));
        subProject.setDeadline(timeManager.calculateEndDate(subProject.getStartDate(), subProject.getDays()));
    }
}