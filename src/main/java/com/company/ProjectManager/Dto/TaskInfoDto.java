package com.company.ProjectManager.Dto;


import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.TaskInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskInfoDto {
    private Long id;
    private String task;


    public TaskInfoDto(Long id, String taskName) {
        this.id = id;
        this.task = taskName;
    }
    public static TaskInfoDto toTaskInfoDto(TaskInfo taskInfo) {
        return new TaskInfoDto(taskInfo.getId(), taskInfo.getTask());
    }
}