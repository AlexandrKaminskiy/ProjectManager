package com.company.ProjectManager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class TaskInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String task;

    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectInfo project;

    public TaskInfo() {
    }

    public TaskInfo(Long id, String task, ProjectInfo project) {
        this.id = id;
        this.task = task;
        this.project = project;
    }
}
