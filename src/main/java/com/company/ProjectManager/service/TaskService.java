package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.TaskInfoDto;
import com.company.ProjectManager.exceptions.InvalidHttpBodyException;
import com.company.ProjectManager.exceptions.ProjectNotFoundException;
import com.company.ProjectManager.exceptions.TaskNotFoundException;
import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ProjectInfoRepo projectInfoRepo;

    public TaskInfoDto addNewTask(Long projectId, TaskInfoDto taskInfoDto) {
        checkTaskRequest(taskInfoDto);
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTask(taskInfoDto.getTask());
        taskInfo.setIsDeleted(false);
        taskInfo.setProject(projectInfoRepo.getById(projectId));
        taskRepo.save(taskInfo);
        taskInfoDto.setId(taskInfo.getId());
        return taskInfoDto;
    }

    public void deleteProjectTasks(Long projectId) {
        var tasks = taskRepo.findTaskInfoByProjectIdAndIsDeleted(projectId,false, Pageable.unpaged());
        if (tasks == null) throw new TaskNotFoundException();
        for (var task : tasks) {
            task.setIsDeleted(true);
            taskRepo.save(task);
        }
    }

    public List<TaskInfo> findProjectTasks(Long projectId) {
        return taskRepo.findTaskInfoByProjectIdAndIsDeleted(projectId,false, Pageable.unpaged());
    }

    public void deleteTaskFromProject(Long taskId) {
        if (!taskRepo.existsById(taskId)) throw new TaskNotFoundException();
        taskRepo.deleteById(taskId);
    }

    public List<TaskInfoDto> findProjectTasksDto(Long id,Pageable pageable) {
        ArrayList<TaskInfoDto> taskInfoDtos = new ArrayList<>();
        var tasks = taskRepo.findTaskInfoByProjectIdAndIsDeleted(id,false, pageable);
        if (tasks == null) throw new TaskNotFoundException();
        tasks.forEach((p) -> taskInfoDtos.add(new TaskInfoDto(p.getId(),p.getTask())));
        return taskInfoDtos;
    }

    public TaskInfoDto findProjectTask(Long id, Long taskId) {
        TaskInfoDto taskInfoDto = null;
        if (!taskRepo.getById(taskId).getIsDeleted()) {
            taskInfoDto = new TaskInfoDto(taskRepo.getById(taskId).getId(), taskRepo.getById(taskId).getTask());
        }
        return taskInfoDto;
    }

    public TaskInfoDto updateTask(Long projectId,Long taskId, TaskInfoDto taskInfoDto) {
        checkTaskRequest(taskInfoDto);
        TaskInfo dbTask = taskRepo.getByIdAndIsDeleted(taskId,false);
        if (dbTask == null) throw new TaskNotFoundException();
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTask(taskInfoDto.getTask());
        taskInfo.setProject(projectInfoRepo.getById(projectId));

        BeanUtils.copyProperties(taskInfo,dbTask,"id","isDeleted");
        taskRepo.save(dbTask);
        return taskInfoDto;
    }
    private void checkTaskRequest(TaskInfoDto taskInfoDto) {
        if (taskInfoDto.getTask() == null ) {
            throw new InvalidHttpBodyException();
        }
    }
}
