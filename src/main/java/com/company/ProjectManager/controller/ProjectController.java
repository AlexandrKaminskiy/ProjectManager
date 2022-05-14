package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.Dto.TaskInfoDto;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.ProjectServise;
import com.company.ProjectManager.service.TaskService;
import com.company.ProjectManager.service.UserService;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public List<ProjectInfoDto> projectList(@AuthenticationPrincipal User user,
                                            Pageable pageable) {
        return projectServise.findProjects(user,pageable);
    }

    @GetMapping("{id}")
    public ProjectInfoDto project(@PathVariable Long id,
                                  @AuthenticationPrincipal User user) {
        return projectServise.projectInfo(user,id);
    }

    @GetMapping("filter")
    public List<ProjectInfoDto> projectFilter(@AuthenticationPrincipal User user,
                                              Pageable pageable, Condition condition) {

        return projectServise.findProjectsByFilter(user,pageable);
    }

//    @GetMapping("filter")
//    public List<ProjectInfoDto> projectFilter(@RequestParam(value = "search", required = false) String search) {
//        List params = new ArrayList();
//        if (search != null) {
//            Pattern pattern = Pattern.compile("(\w+?)(:|<|>)(\w+?),");
//            Matcher matcher = pattern.matcher(search + ",");
//            while (matcher.find()) {
//                params.add(new SearchCriteria(matcher.group(1),
//                        matcher.group(2), matcher.group(3)));
//            }
//        }
//        return api.searchUser(params);
//    }


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
    public List<TaskInfoDto> showTasks(@PathVariable Long id,
                                       Pageable pageable) {
        return taskService.findProjectTasksDto(id,pageable);
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