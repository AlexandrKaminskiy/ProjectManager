package com.company.ProjectManager.service;

import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNull;
@SpringBootTest
class ProjectServiseTest {
    @Autowired
    private ProjectServise projectServise;

    @MockBean
    private ProjectServise mockProjectService;

    @MockBean
    private ProjectInfoRepo projectInfoRepo;

    @Test
    void projectInfo() {
        Mockito.doReturn(false).when(mockProjectService).isBelongToUser(new User(),121212l);
        assertNull(projectServise.projectInfo(new User(),1l));
    }
}