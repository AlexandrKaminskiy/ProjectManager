package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.exceptions.InvalidHttpBodyException;
import com.company.ProjectManager.exceptions.ProjectNotFoundException;
import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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


    public ProjectInfoDto createProject(ProjectInfoDto projectInfoDto, User user) {
        if (projectInfoDto.getName() == null) {
            throw new InvalidHttpBodyException();
        }
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setName(projectInfoDto.getName());
        projectInfo.setIsDeleted(false);
        ArrayList<User> users = new ArrayList<>();
        List<UserDto> authors = projectInfoDto.getAuthor();
        if (authors == null) authors = new ArrayList<>();
        authors.add(new UserDto(user.getUsername(),user.getRoles()));
        authors = authors.stream()
                .distinct()
                .toList();
        for (var author : authors) {
            users.add(userRepo.findByUsername(author.getUsername()));
        }
        projectInfo.setAuthor(users);
        projects.save(projectInfo);
        projectInfoDto.setId(projectInfo.getId());
        return projectInfoDto;
    }

    public List<ProjectInfoDto> findProjects(User user, Pageable pageable) {
        Stream<ProjectInfo> result;
        var projectInfoDtos = new ArrayList<ProjectInfoDto>();
        if (user.getRoles().contains(Role.ADMIN)) {
            result = projects.findByIsDeleted(false, pageable).stream();
        } else {
            result = projects.findByAuthorAndIsDeleted(user,false, pageable).stream();
        }
        if (result == null) {
            throw new ProjectNotFoundException();
        }
        result.forEach((p) -> projectInfoDtos.add(new ProjectInfoDto(p.getId(),p.getName(),p.getAuthor(),taskService.findProjectTasks(p.getId()),p.getCompanyName(),p.getIsReady())));
        return projectInfoDtos;
    }

    public void deleteProject(Long id, User user) {
        if(isBelongToUser(user, id)) {
            taskService.deleteProjectTasks(id);
            ProjectInfo dbProject = projects.findByIdAndIsDeleted(id, false);
            dbProject.setIsDeleted(true);
            projects.save(dbProject);
        }
    }

    public ProjectInfoDto projectInfo(User user,Long id) {
        if (isBelongToUser(user,id)) {
            var p = projects.findByIdAndIsDeleted(id, false);
            if (p == null) {
                throw new ProjectNotFoundException();
            }
            return new ProjectInfoDto(p.getId(),p.getName(),p.getAuthor(),taskService.findProjectTasks(p.getId()),p.getCompanyName(),p.getIsReady());
        }
        return null;
    }

    public ProjectInfoDto updateProject(Long id, ProjectInfoDto projectInfoDto, User user) {
        if (isBelongToUser(user, id)) {
            ProjectInfo dbProject = projects.findByIdAndIsDeleted(id, false);
            ProjectInfo projectInfo = new ProjectInfo();
            if (projectInfoDto.getName() == null) throw new InvalidHttpBodyException();
            projectInfo.setName(projectInfoDto.getName());
            projectInfo.setCompanyName(projectInfoDto.getCompanyName());
            projectInfo.setIsReady(projectInfoDto.getIsReady());
            ArrayList<User> users = new ArrayList<>();
            var authors = projectInfoDto.getAuthor();
            if (authors == null) throw new InvalidHttpBodyException();
                else authors = authors.stream().distinct().toList();
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

            return projectInfoDto;
        }
        return null;
    }

    private boolean isBelongToUser(User user, Long id) {
        var project = projects.findByIdAndIsDeleted(id, false);
        if (project == null) {
            throw new ProjectNotFoundException();
        }
        if(project.getAuthor().contains(user) || user.getRoles().contains(Role.ADMIN)) {
            return true;
        }
        return false;
    }

    public List<ProjectInfoDto> findProjectsByFilter(User user,String companyName, String name, Pageable pageable) {
        List<ProjectInfo> result = new ArrayList<>();
        if (!user.getRoles().contains(Role.ADMIN)) {
            result = projects.findByIsDeletedAndCompanyNameContainsAndNameContainsAndAuthor(false,
                            companyName, name, user, pageable);
        } else {
            result = projects.findByIsDeletedAndCompanyNameContainsAndNameContains(false,
                    companyName, name, pageable);
        }
        var projectInfoDtos = new ArrayList<ProjectInfoDto>();
        result.forEach((p) -> projectInfoDtos.add(new ProjectInfoDto(p.getId(),p.getName(),p.getAuthor(),taskService.findProjectTasks(p.getId()),p.getCompanyName(),p.getIsReady())));
        return projectInfoDtos;

    }
}