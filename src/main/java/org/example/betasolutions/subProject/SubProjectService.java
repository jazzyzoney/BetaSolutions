package org.example.betasolutions.subProject;

import org.springframework.stereotype.Service;

@Service
public class SubProjectService {
    SubProjectRepository subProjectRepository;

    public SubProjectService(SubProjectRepository subProjectRepository){
        this.subProjectRepository = subProjectRepository;
    }
}
