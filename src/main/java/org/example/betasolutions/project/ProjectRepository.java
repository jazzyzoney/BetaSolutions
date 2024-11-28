package org.example.betasolutions.project;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    public Project getProject(int projectID){
        return new Project();
    }
}
