package com.company.ProjectManager.service;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.UserRepo;
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

    @Autowired
    UserRepo userRepo;

    public void createProject(String name, User user) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setName(name);
        projectInfo.setAuthor(List.of(user));
        projects.save(projectInfo);
    }

    public List<ProjectInfo> findProjects(User user) {
        if (user.getRoles().contains(Role.ADMIN)) return projects.findAll();
        return projects.findByAuthor(user);
    }

    public void deleteProject(Long id) {
        projects.deleteById(id);
    }

    public List<User> findProjectUsers(Long projectInfo) {

        return projects.getById(projectInfo).getAuthor();
    }

    public void addUserToProject(Long id, String newUser) {
        var project =  projects.getById(id);
        var authors = project.getAuthor();
        authors.add(userRepo.findByUsername(newUser));
        project.setAuthor(authors);
        projects.save(project);

    }

    public String getProjectName(Long id) {
        return projects.getById(id).getName();
    }

    public void changeProjectName(Long id, String newName) {
        if (newName != null) {
            var project = projects.getById(id);
            project.setName(newName);
            projects.save(project);
        }
    }

    public void deleteUserFromProject(Long id, Long userId) {
        var project = projects.getById(id);
        if (project.getAuthor().size() > 1) {
            project.getAuthor().remove(userRepo.getById(userId));
            projects.save(project);
        }
    }


}
