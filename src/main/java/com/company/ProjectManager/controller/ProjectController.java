package com.company.ProjectManager.controller;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.service.ProjectServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@Controller
@RequestMapping()
public class ProjectController {

    @Autowired
    ProjectServise projectServise;

    @GetMapping("projects")
    public String list(Model model) {
        model.addAttribute("projectList",projectServise.findAllProjects());
        return "projects";
    }

    @GetMapping("createprojectform")
    public String createProjectForm() {
        return "createprojectform";
    }

    @PostMapping("createproject")
    public String createProject(@RequestParam String projectName,
                                @AuthenticationPrincipal User user) {
        projectServise.createProject(projectName,user);
        return "redirect:/projects";
    }



}