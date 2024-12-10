package org.example.betasolutions.project;

import org.example.betasolutions.FactoryInterface;
import org.example.betasolutions.ModelInterface;
import org.example.betasolutions.subProject.SubProject;
import org.example.betasolutions.subTask.SubTask;
import org.example.betasolutions.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void insertAssignmentIntoTable(Project project) {
        projectRepository.insertAssignmentIntoTable(project);
    }

    public List<Project> readAllProjects() {
        return projectRepository.readAllProjects();
    }

    public Project readProjectByID(int project_id) {
        return projectRepository.readProjectByID(project_id);
    }

    public void updateProject(Project project, int project_id) {
        projectRepository.updateProject(project, project_id);
    }

    public List<SubProject> getAllSubProjects(int projectID) {
        List<SubProject> subProjects = projectRepository.getAllSubProjects(projectID); //get all subprojects using subprojectRepository.
        return subProjects;
    }

    public List<Task> getAllTasksForSubProject(int projectID, int subProjectID) {
        List<Task> tasks = projectRepository.getAllTasksForSubProject(projectID, subProjectID); //get all tasks for subproject, using taskRepository.
        return tasks;
    }

    public List<Task> getAllTasksBelongingToProject(int projectID) {
        List<Task> tasks = projectRepository.getAllTasksBelongingToProject(projectID);
        return tasks;
    }

    public List<SubTask> getAllSubTasks(int taskID, int projectID) {
        List<SubTask> subTasks = projectRepository.getAllSubTasks(taskID, projectID);
        return subTasks;
    }

}
