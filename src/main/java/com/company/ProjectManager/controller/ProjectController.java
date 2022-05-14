package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.Dto.TaskInfoDto;
import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.ProjectServise;
import com.company.ProjectManager.service.TaskService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
@Validated
public class ProjectController {
    private final ProjectServise projectService;
    private final TaskService taskService;

    public ProjectController(ProjectServise projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping()
    public List<ProjectInfo> projectList(@AuthenticationPrincipal User user,
                                         Pageable pageable) {
        return projectService.findProjects(user,pageable);
    }

    @GetMapping("/{id}")
    public ProjectInfo project(@PathVariable Long id,
                                  @AuthenticationPrincipal User user) {
        return projectService.projectInfo(user,id);
    }

    @GetMapping("/filter")
    public List<ProjectInfo> projectFilter(@AuthenticationPrincipal User user,
                                              @RequestParam(defaultValue =  "") String companyName,
                                              @RequestParam(defaultValue = "") String name,
                                              Pageable pageable) {
        return projectService.findProjectsByFilter(user,companyName,name,pageable);
    }


    @PostMapping()
    public ProjectInfo createProject(@Valid @RequestBody ProjectInfoDto projectInfoDto,
                                        @AuthenticationPrincipal User user) {
        return projectService.createProject(projectInfoDto,user);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id,
                              @AuthenticationPrincipal User user) {
        projectService.deleteProject(id,user);
    }

    @PutMapping("/{id}")
    public ProjectInfo updateProject(@PathVariable Long id,
                                @RequestBody @Valid ProjectInfoDto projectInfoDto,
                                @AuthenticationPrincipal User user) {
        return projectService.updateProject(id, projectInfoDto, user);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskInfo> showTasks(@PathVariable Long id,
                                    Pageable pageable) {
        return taskService.findProjectTasksDto(id,pageable);
    }
    @GetMapping("/{id}/tasks/filter")
    public List<TaskInfo> taskFilter(@AuthenticationPrincipal User user,
                                           @PathVariable Long id,
                                           @RequestParam(defaultValue = "") String name,
                                           Pageable pageable) {
        return taskService.findTasksByName(user,id,name,pageable);
    }
    @PostMapping("/{id}/tasks")
    public TaskInfo addtask(@PathVariable Long id,
                               @RequestBody @Valid TaskInfoDto taskInfoDto) {
        return taskService.addNewTask(id,taskInfoDto);
    }


    @GetMapping("/{id}/tasks/{taskId}")
    public TaskInfo showTasks(@PathVariable Long id,
                                 @PathVariable Long taskId) {
        return taskService.findProjectTask(id,taskId);
    }

    @DeleteMapping("/{id}/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTaskFromProject(taskId);
    }

    @PutMapping("/{id}/tasks/{taskId}")
    public TaskInfo updateTask(@PathVariable Long id,
                                     @PathVariable Long taskId,
                                     @RequestBody @Valid TaskInfoDto taskInfoDto) {
        return taskService.updateTask(id, taskId, taskInfoDto);
    }
}