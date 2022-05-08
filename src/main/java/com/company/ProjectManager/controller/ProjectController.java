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
//        System.out.println(id);
        return "redirect:/projects";
    }

    @GetMapping("projectinfo/{id}")
    public String projectInfoForm(@PathVariable Long id, Model model) {
        model.addAttribute("taskList",taskService.findProjectTasks(id));
        model.addAttribute("userList",projectServise.findProjectUsers(id));
        return "project_info";
    }




}