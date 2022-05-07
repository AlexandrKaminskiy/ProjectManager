package com.company.ProjectManager.service;

import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;

    @Autowired
    ProjectInfoRepo projectInfoRepo;

    public void addNewTask(Long projectId, String task) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTask(task);
        taskInfo.setProject(projectInfoRepo.getById(projectId));
        taskRepo.save(taskInfo);
    }

    public void deleteProjectTasks(Long projectId) {
        var o = taskRepo.findTaskInfoByProjectId(projectId);
        taskRepo.deleteAll(o);
    }
}
