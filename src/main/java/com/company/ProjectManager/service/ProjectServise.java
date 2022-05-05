package com.company.ProjectManager.service;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProjectServise {
    @Autowired
    ProjectInfoRepo projects;

    public void createProject(String name, User user) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setName(name);
        projectInfo.setAuthor(List.of(user));
        projects.save(projectInfo);
    }

    public void addUser(ProjectInfo projectInfo) {
//        projectInfo.
    }
    public List<ProjectInfo> findAllProjects() {
        return projects.findAll();
    }
}
