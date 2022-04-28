package com.company.ProjectManager.model;

import javax.persistence.*;

@Entity
public class TaskInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String task;
//    private ProjectInfo project;

    public TaskInfo() {
    }

    public TaskInfo(Long id, String task, ProjectInfo project) {
        this.id = id;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

}
