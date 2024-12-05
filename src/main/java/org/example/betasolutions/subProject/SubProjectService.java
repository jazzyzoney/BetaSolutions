package org.example.betasolutions.subProject;

import org.springframework.stereotype.Service;

@Service
public class SubProjectService {
    private SubProjectRepository subProjectRepository;

    public SubProjectService(SubProjectRepository subProjectRepository){
        this.subProjectRepository = subProjectRepository;
    }
    public void insertIntoSubProject(SubProject subProject){
        subProjectRepository.insertIntoSubProject(subProject);
    }
    public void readAllSubProjects(int projectID){
        subProjectRepository.readAllSubProjects(projectID);
    }
    public void readSubProjectByID(int subProjectID){
        subProjectRepository.readSubProjectByID(subProjectID);
    }
}
