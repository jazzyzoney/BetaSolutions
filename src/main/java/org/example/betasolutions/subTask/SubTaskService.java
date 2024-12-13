package org.example.betasolutions.subTask;

import org.example.betasolutions.TimeManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {
    private final TimeManager timeManager;
    private SubTaskRepository subTaskRepository;

    public SubTaskService(SubTaskRepository subTaskRepository, TimeManager timeManager){
        this.subTaskRepository = subTaskRepository;
        this.timeManager = timeManager;
    }
    public void createSubTask(SubTask subTask){
        calculateDeadline(subTask);
        subTaskRepository.addSubTaskToTask(subTask);
    }

    public List<SubTask> readAllSubTasks(int ProjectID, int TaskID){
        return subTaskRepository.readAllSubTasks(ProjectID, TaskID);
    }
    public void deleteSubTask(int subTaskID){
        subTaskRepository.deleteSubTask(subTaskID);
    }
    public void calculateDeadline(SubTask subTask){
        subTask.setTotalDays(timeManager.calculateDays(subTask.getHours()));
        subTask.setDeadline(timeManager.calculateEndDate(subTask.getStartDate(), subTask.getDays()));
    }
}
