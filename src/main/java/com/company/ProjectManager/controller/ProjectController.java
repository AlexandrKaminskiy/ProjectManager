package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.Dto.TaskInfoDto;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.ProjectServise;
import com.company.ProjectManager.service.TaskService;
import com.company.ProjectManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {
    @Autowired
    ProjectServise projectService;
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;

    @GetMapping()
    public List<ProjectInfoDto> projectList(@AuthenticationPrincipal User user,
                                            Pageable pageable) {
        return projectService.findProjects(user,pageable);
    }

    @GetMapping("{id}")
    public ProjectInfoDto project(@PathVariable Long id,
                                  @AuthenticationPrincipal User user) {
        return projectService.projectInfo(user,id);
    }

    @GetMapping("filter")
    public List<ProjectInfoDto> projectFilter(@AuthenticationPrincipal User user,
                                              @RequestParam(defaultValue =  "") String companyName,
                                              @RequestParam(defaultValue = "") String name,
                                              Pageable pageable) {
        return projectService.findProjectsByFilter(user,companyName,name,pageable);
    }


    @PostMapping()
    public ProjectInfoDto createProject(@RequestBody ProjectInfoDto projectInfoDto,
                                        @AuthenticationPrincipal User user) {
        projectService.createProject(projectInfoDto,user);
        return projectService.projectInfo(user,projectInfoDto.getId());
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable Long id,
                              @AuthenticationPrincipal User user) {
        projectService.deleteProject(id,user);
    }

    @PutMapping("{id}")
    public ProjectInfoDto updateProject(@PathVariable Long id,
                                @RequestBody ProjectInfoDto projectInfoDto,
                                @AuthenticationPrincipal User user) {
        return projectService.updateProject(id, projectInfoDto, user);
    }

    @GetMapping("{id}/tasks")
    public List<TaskInfoDto> showTasks(@PathVariable Long id,
                                       Pageable pageable) {
        return taskService.findProjectTasksDto(id,pageable);
    }
    @GetMapping("{id}/tasks/filter")
    public List<TaskInfoDto> taskFilter(@AuthenticationPrincipal User user,
                                           @PathVariable Long id,
                                           @RequestParam(defaultValue = "") String name,
                                           Pageable pageable) {
        return taskService.findTasksByName(user,id,name,pageable);
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