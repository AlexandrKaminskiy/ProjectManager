package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProjectServise {
    @Autowired
    ProjectInfoRepo projects;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TaskService taskService;

    public void createProject(ProjectInfoDto projectInfoDto, User user) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setName(projectInfoDto.getName());
        ArrayList<User> users = new ArrayList<>();
        var authors = projectInfoDto.getAuthor();
        for (var author : authors) {
            users.add(userRepo.getById(author.getId()));
        }
        projectInfo.setAuthor(users);
        projects.save(projectInfo);
    }

    public List<ProjectInfoDto> findProjects(User user) {
        Stream<ProjectInfo> result;
        var projectInfoDto = new ArrayList<ProjectInfoDto>();
        if (user.getRoles().contains(Role.ADMIN)) {
            result = projects.findAll().stream();
        } else result = projects.findByAuthor(user).stream();
        result.forEach((p) -> projectInfoDto.add(new ProjectInfoDto(p.getId(),p.getName(),p.getAuthor(),taskService.findProjectTasks(p.getId()))));
        return projectInfoDto;
    }

    public void deleteProject(Long id, User user) {
        if(isBelongToUser(user, id)) {
            taskService.deleteProjectTasks(id);
            projects.deleteById(id);
        }
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

    public ProjectInfoDto projectInfo(Long id) {
        var p = projects.getById(id);
        return new ProjectInfoDto(p.getId(),p.getName(),p.getAuthor(),taskService.findProjectTasks(p.getId()));
    }
    ///ЗДЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕСЬЪЪЪЪ
    public void updateProject(Long id, ProjectInfoDto projectInfoDto, User user) {
        if (isBelongToUser(user, id)) {
            ProjectInfo dbProject = projects.getById(id);

            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setName(projectInfoDto.getName());
            ArrayList<User> users = new ArrayList<>();
            var authors = projectInfoDto.getAuthor();
            for (var author : authors) {
                users.add(userRepo.getById(author.getId()));
            }
            projectInfo.setAuthor(users);

//            taskService.

            BeanUtils.copyProperties(dbProject,projectInfo,"id");
            projects.save(dbProject);
        }
    }

    private boolean isBelongToUser(User user, Long id) {
        if(projects.getById(id).getAuthor().contains(user) || user.getRoles().contains(Role.ADMIN)) {
            return true;
        }
        return false;
    }
}
