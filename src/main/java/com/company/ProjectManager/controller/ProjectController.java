package com.company.ProjectManager.controller;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.ProjectServise;
import com.company.ProjectManager.service.TaskService;
import com.company.ProjectManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("projects")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class ProjectController {

    @Autowired
    ProjectServise projectServise;

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @GetMapping()
    public String list(Model model,
                       @AuthenticationPrincipal User user) {
        model.addAttribute("projectList",projectServise.findProjects(user));
        return "projects";
    }

    @GetMapping("/createprojectform")
    public String createProjectForm() {
        return "createprojectform";
    }

    @PostMapping("createproject")
    public String createProject(@RequestParam String projectName,
                                @AuthenticationPrincipal User user) {
        projectServise.createProject(projectName,user);
        return "redirect:/projects";
    }

    @GetMapping("delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        taskService.deleteProjectTasks(id);
        projectServise.deleteProject(id);
        return "redirect:/projects";
    }

    @GetMapping("{id}")
    public String addTaskForm(@PathVariable Long id) {
        return "addtask";
    }

    @PostMapping("{id}/addtask")
    public String addTask(@PathVariable Long id,
                          @RequestParam String task) {
        taskService.addNewTask(id, task);
        return "redirect:/projects";
    }

    @GetMapping("projectinfo/{id}")
    public String projectInfoForm(@PathVariable Long id, Model model) {
        model.addAttribute("projectName",projectServise.getProjectName(id));
        model.addAttribute("taskList",taskService.findProjectTasks(id));
        model.addAttribute("userList",projectServise.findProjectUsers(id));
        return "project_info";
    }

    @PostMapping("projectinfo/addusr/{id}")
    public String addUser(@PathVariable Long id,
                          @RequestParam String newUser) {
        projectServise.addUserToProject(id,newUser);

        return "redirect:/projects/projectinfo/{id}";
    }

    @GetMapping("projectinfo/deleteusr/{id}/{userId}")
    public String deleteUser(@PathVariable Long id,
                             @PathVariable Long userId) {
        projectServise.deleteUserFromProject(id,userId);
        return "redirect:/projects/projectinfo/{id}";
    }

    @GetMapping("projectinfo/deletetask/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        System.out.println(taskId);
        taskService.deleteTaskFromProject(taskId);
        return "redirect:/projects";
    }

    @GetMapping("projectinfo/changetask/{taskId}")
    public String changeTaskForm(@PathVariable Long taskId) {
        return "changetask";
    }

    @PostMapping("projectinfo/changetask/{taskId}")
    public String changeTask(@PathVariable Long taskId,
                             @RequestParam String taskName) {
        taskService.changeTask(taskId,taskName);
        return "redirect:/projects";
    }



    @PostMapping("projectinfo/changeName/{id}")
    public String changeName(@PathVariable Long id,
                             @RequestParam String newName) {
        projectServise.changeProjectName(id,newName);
        return "redirect:/projects/projectinfo/{id}";
    }
}