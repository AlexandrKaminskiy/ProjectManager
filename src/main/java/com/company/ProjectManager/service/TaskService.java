package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.TaskInfoDto;
import com.company.ProjectManager.exceptions.TaskNotFoundException;
import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    private final ProjectInfoRepo projectInfoRepo;

    public TaskService(TaskRepo taskRepo, ProjectInfoRepo projectInfoRepo) {
        this.taskRepo = taskRepo;
        this.projectInfoRepo = projectInfoRepo;
    }

    public TaskInfo addNewTask(Long projectId, TaskInfoDto taskInfoDto) {

        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTask(taskInfoDto.getTask());
        taskInfo.setIsDeleted(false);
        taskInfo.setProject(projectInfoRepo.getById(projectId));
        taskRepo.save(taskInfo);
        return taskInfo;
    }

    public void deleteProjectTasks(Long projectId) {
        var tasks = taskRepo.findTaskInfoByProjectIdAndIsDeleted(projectId,false, Pageable.unpaged());
        if (tasks == null) throw new TaskNotFoundException();
        for (var task : tasks) {
            task.setIsDeleted(true);
            taskRepo.save(task);
        }
    }

    public void deleteTaskFromProject(Long taskId) {
        if (!taskRepo.existsById(taskId)) throw new TaskNotFoundException();
        taskRepo.deleteById(taskId);
    }

    public List<TaskInfo> findProjectTasksDto(Long id,Pageable pageable) {
        var tasks = taskRepo.findTaskInfoByProjectIdAndIsDeleted(id,false, pageable);
        if (tasks == null) throw new TaskNotFoundException();
        return tasks;
    }

    public TaskInfo findProjectTask(Long id, Long taskId) {
        return taskRepo.getById(taskId);
    }

    public TaskInfo updateTask(Long projectId,Long taskId, TaskInfoDto taskInfoDto) {
        TaskInfo dbTask = taskRepo.getByIdAndIsDeleted(taskId,false);
        if (dbTask == null) throw new TaskNotFoundException();
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTask(taskInfoDto.getTask());
        taskInfo.setProject(projectInfoRepo.getById(projectId));

        BeanUtils.copyProperties(taskInfo,dbTask,"id","isDeleted");
        taskRepo.save(dbTask);
        return dbTask;
    }


    public List<TaskInfo> findTasksByName(User user, Long id, String name, Pageable pageable) {
        ArrayList<TaskInfo> taskInfos = new ArrayList<>();
        var tasks = taskRepo.findTaskInfoByProjectIdAndIsDeletedAndTaskContains(id,false, name, pageable);
        if (tasks == null) throw new TaskNotFoundException();
        return taskInfos;
    }
}
