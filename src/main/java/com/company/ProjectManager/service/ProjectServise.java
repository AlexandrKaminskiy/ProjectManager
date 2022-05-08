package com.company.ProjectManager.service;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public List<ProjectInfo> findProjects(User user) {
        return projects.findByAuthor(user);
    }

    public void deleteProject(Long id) {
        projects.deleteById(id);
    }

    public List<User> findProjectUsers(Long projectInfo) {

        return projects.getById(projectInfo).getAuthor();
    }

}
