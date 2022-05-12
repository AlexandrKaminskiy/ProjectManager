package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.Dto.TaskInfoDto;
import com.company.ProjectManager.desirealizer.JsonSerializer;
import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.ProjectServise;
import com.company.ProjectManager.service.TaskService;
import com.company.ProjectManager.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
        return projectServise.projectInfo(id);
    }

    @PostMapping("createproject")
    public String createProject(@RequestBody ProjectInfoDto projectInfoDto,
                                @AuthenticationPrincipal User user) {
        projectServise.createProject(projectInfoDto,user);
        return "redirect:/projects";
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable Long id,
                              @AuthenticationPrincipal User user) {
        projectServise.deleteProject(id,user);
    }

    @GetMapping("{id}/tasks")
    public String getTasks(@PathVariable Long id) {
        TaskInfoDto a = new TaskInfoDto(1l,"2");
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(a);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;//taskService.findProjectTasks(id);
    }

    @PutMapping("{id}")
    public String updateProject(@PathVariable Long id,
                                @RequestBody ProjectInfoDto projectInfoDto,
                                @AuthenticationPrincipal User user) {
        projectServise.updateProject(id, projectInfoDto, user);
        return "redirect:/projects";
    }

    @GetMapping("/q")
    public String q(@RequestBody TaskInfoDto projectInfo) {

//        String json = projectInfo.getBody();
        JsonSerializer creator = new JsonSerializer();
        return "cool";
    }

//    @PostMapping("{id}/addtask")
//    public String addTask(@PathVariable Long id,
//                          @RequestParam String task) {
//        taskService.addNewTask(id, task);
//        return "redirect:/projects";
//    }

//    @GetMapping("projectinfo/{id}")
//    public String projectInfoForm(@PathVariable Long id, Model model) {
//        model.addAttribute("projectName",projectServise.getProjectName(id));
//        model.addAttribute("taskList",taskService.findProjectTasks(id));
//        model.addAttribute("userList",projectServise.findProjectUsers(id));
//        return "project_info";
//    }

//    @PostMapping("projectinfo/addusr/{id}")
//    public String addUser(@PathVariable Long id,
//                          @RequestParam String newUser) {
//        projectServise.addUserToProject(id,newUser);
//
//        return "redirect:/projects/projectinfo/{id}";
//    }

//    @GetMapping("projectinfo/deleteusr/{id}/{userId}")
//    public String deleteUser(@PathVariable Long id,
//                             @PathVariable Long userId) {
//        projectServise.deleteUserFromProject(id,userId);
//        return "redirect:/projects/projectinfo/{id}";
//    }

//    @GetMapping("projectinfo/deletetask/{taskId}")
//    public String deleteTask(@PathVariable Long taskId) {
//        System.out.println(taskId);
//        taskService.deleteTaskFromProject(taskId);
//        return "redirect:/projects";
//    }

//    @GetMapping("projectinfo/changetask/{taskId}")
//    public String changeTaskForm(@PathVariable Long taskId) {
//        return "changetask";
//    }

//    @PostMapping("projectinfo/changetask/{taskId}")
//    public String changeTask(@PathVariable Long taskId,
//                             @RequestParam String taskName) {
//        taskService.changeTask(taskId,taskName);
//        return "redirect:/projects";
//    }

//    @PostMapping("projectinfo/changeName/{id}")
//    public String changeName(@PathVariable Long id,
//                             @RequestParam String newName) {
//        projectServise.changeProjectName(id,newName);
//        return "redirect:/projects/projectinfo/{id}";
//    }
}