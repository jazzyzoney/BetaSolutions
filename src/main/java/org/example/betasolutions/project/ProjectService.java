package org.example.betasolutions.project;

import org.example.betasolutions.login.LoginService;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    ProjectRepository projectRepository;

    public LoginController(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
}
