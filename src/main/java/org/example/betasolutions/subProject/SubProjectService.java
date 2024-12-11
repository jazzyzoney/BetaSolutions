package org.example.betasolutions.subProject;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {
    private SubProjectRepository subProjectRepository;

    public SubProjectService(SubProjectRepository subProjectRepository){
        this.subProjectRepository = subProjectRepository;
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

}
