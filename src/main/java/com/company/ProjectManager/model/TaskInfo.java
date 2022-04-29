package com.company.ProjectManager.model;

import javax.persistence.*;

@Entity
@Table
public class TaskInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String task;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectInfo project;

    public ProjectInfo getProject() {
        return project;
    }

    public void setProject(ProjectInfo project) {
        this.project = project;
    }

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
