package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.TaskInfoDto;
import com.company.ProjectManager.exceptions.TaskNotFoundException;
import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.TaskRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @MockBean
    private TaskRepo taskRepo;
    @MockBean
    private TaskInfo taskInfo;
    @MockBean
    private ProjectInfoRepo projectInfoRepo;
    @Test
    void addNewTask() {
        TaskInfoDto dto = new TaskInfoDto(1l,"111");
        var testDto = taskService.addNewTask(1l,dto);
        assertEquals("111",testDto.getTaskName());
    }

    @Test
    void deleteTask() {
        assertThrows(TaskNotFoundException.class,()->taskService.deleteTaskFromProject(12312l));
    }

}