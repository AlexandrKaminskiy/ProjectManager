package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.Dto.TaskInfoDto;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.ProjectServise;
import com.company.ProjectManager.service.TaskService;
import com.company.ProjectManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {
    @Autowired
    ProjectServise projectServise;
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;

    @GetMapping()
    public List<ProjectInfoDto> projectList(@AuthenticationPrincipal User user) {
        return projectServise.findProjects(user);
    }

    @GetMapping("{id}")
    public ProjectInfoDto project(@PathVariable Long id,
                                  @AuthenticationPrincipal User user) {
        return projectServise.projectInfo(user,id);
    }

    @PostMapping()
    public ProjectInfoDto createProject(@RequestBody ProjectInfoDto projectInfoDto,
                                        @AuthenticationPrincipal User user) {
        projectServise.createProject(projectInfoDto,user);
        return projectServise.projectInfo(user,projectInfoDto.getId());
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable Long id,
                              @AuthenticationPrincipal User user) {
        projectServise.deleteProject(id,user);
    }

    @PutMapping("{id}")
    public ProjectInfoDto updateProject(@PathVariable Long id,
                                @RequestBody ProjectInfoDto projectInfoDto,
                                @AuthenticationPrincipal User user) {
        return projectServise.updateProject(id, projectInfoDto, user);
    }

    @GetMapping("{id}/tasks")
    public List<TaskInfoDto> showTasks(@PathVariable Long id) {
        return taskService.findProjectTasksDto(id);
    }

    @PostMapping("{id}/tasks")
    public TaskInfoDto addtask(@PathVariable Long id,
                               @RequestBody TaskInfoDto taskInfoDto) {
        return taskService.addNewTask(id,taskInfoDto);
    }


    @GetMapping("{id}/tasks/{taskId}")
    public TaskInfoDto showTasks(@PathVariable Long id,
                                 @PathVariable Long taskId) {
        return taskService.findProjectTask(id,taskId);
    }

    @DeleteMapping("{id}/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTaskFromProject(taskId);
    }

    @PutMapping("{id}/tasks/{taskId}")
    public TaskInfoDto updateTask(@PathVariable Long id,
                                     @PathVariable Long taskId,
                                     @RequestBody TaskInfoDto taskInfoDto) {
        return taskService.updateTask(id, taskId, taskInfoDto);
    }
}