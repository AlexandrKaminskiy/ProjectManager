package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.exceptions.ProjectNotFoundException;
import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServise {

    private final ProjectInfoRepo projects;
    private final UserRepo userRepo;
    private final TaskService taskService;

    public ProjectServise(ProjectInfoRepo projects, UserRepo userRepo, TaskService taskService) {
        this.projects = projects;
        this.userRepo = userRepo;
        this.taskService = taskService;
    }


    public ProjectInfo createProject(ProjectInfoDto projectInfoDto, User user) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setName(projectInfoDto.getName());
        projectInfo.setIsDeleted(false);
        ArrayList<User> users = new ArrayList<>();
        List<UserDto> authors = projectInfoDto.getAuthor();
        if (authors == null) authors = new ArrayList<>();
        authors.add(new UserDto(user.getUsername(),user.getRoles()));
        authors = projectInfoDto.getAuthor().stream()
                .collect(Collectors.toMap(UserDto::getUsername, p -> p, (p, q) -> p))
                .values().stream()
                .toList();
        for (var author : authors) {
            users.add(userRepo.findByUsername(author.getUsername()));
        }
        projectInfo.setAuthor(users);
        projects.save(projectInfo);
        return projectInfo;
    }

    public List<ProjectInfo> findProjects(User user, Pageable pageable) {
        List<ProjectInfo> result;
        if (user.getRoles().contains(Role.ADMIN)) {
            result = projects.findByIsDeleted(false, pageable);
        } else {
            result = projects.findByAuthorAndIsDeleted(user,false, pageable);
        }
        if (result == null) {
            throw new ProjectNotFoundException();
        }
        return result;
    }

    public void deleteProject(Long id, User user) {
        if(isBelongToUser(user, id)) {
            taskService.deleteProjectTasks(id);
            ProjectInfo dbProject = projects.findByIdAndIsDeleted(id, false);
            dbProject.setIsDeleted(true);
            projects.save(dbProject);
        }
    }

    public ProjectInfo projectInfo(User user,Long id) {
        if (isBelongToUser(user,id)) {
            var p = projects.findByIdAndIsDeleted(id, false);
            if (p == null) {
                throw new ProjectNotFoundException();
            }
            return p;
        }
        return null;
    }

    public ProjectInfo updateProject(Long id, ProjectInfoDto projectInfoDto, User user) {
        if (isBelongToUser(user, id)) {
            ProjectInfo dbProject = projects.findByIdAndIsDeleted(id, false);
            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setName(projectInfoDto.getName());
            projectInfo.setCompanyName(projectInfoDto.getCompanyName());
            projectInfo.setIsReady(projectInfoDto.getIsReady());
            ArrayList<User> users = new ArrayList<>();
            var authors = projectInfoDto.getAuthor().stream()
                    .collect(Collectors.toMap(UserDto::getUsername, p -> p, (p, q) -> p))
                    .values();

            for (var author : authors) {
                users.add(userRepo.findByUsername(author.getUsername()));
            }
            projectInfo.setAuthor(users);
            taskService.deleteProjectTasks(id);
            var tasks = projectInfoDto.getTasks();
            if (tasks != null) {
                for (var task : tasks) {
                    taskService.addNewTask(id, task);
                }
            }
            BeanUtils.copyProperties(projectInfo,dbProject,"id","isDeleted");
            projects.save(dbProject);

            return dbProject;
        }
        return null;
    }

    public boolean isBelongToUser(User user, Long id) {
        var project = projects.findByIdAndIsDeleted(id, false);
        if (project == null) {
            throw new ProjectNotFoundException();
        }
        if(project.getAuthor().contains(user) || user.getRoles().contains(Role.ADMIN)) {
            return true;
        }
        return false;
    }

    public List<ProjectInfo> findProjectsByFilter(User user,String companyName, String name, Pageable pageable) {
        List<ProjectInfo> result;
        if (!user.getRoles().contains(Role.ADMIN)) {
            result = projects.findByIsDeletedAndCompanyNameContainsAndNameContainsAndAuthor(false,
                            companyName, name, user, pageable);
        } else {
            result = projects.findByIsDeletedAndCompanyNameContainsAndNameContains(false,
                    companyName, name, pageable);
        }
        return result;

    }
}