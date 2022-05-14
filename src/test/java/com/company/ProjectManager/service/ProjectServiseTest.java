package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.ProjectInfoDto;
import com.company.ProjectManager.exceptions.InvalidHttpBodyException;
import com.company.ProjectManager.exceptions.ProjectNotFoundException;
import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProjectServiseTest {
    @Autowired
    ProjectServise projectServise;

    @MockBean
    ProjectInfoRepo projectInfoRepo;

    @MockBean
    User user;
    @Test
    void updateProject() {
        assertThrows(ProjectNotFoundException.class,() -> projectServise.projectInfo(user,1l));
    }

    @Test
    void createProject() {
        ProjectInfoDto projectInfoDto = new ProjectInfoDto();
        assertThrows(InvalidHttpBodyException.class,() -> projectServise.createProject(projectInfoDto,user));
    }
}