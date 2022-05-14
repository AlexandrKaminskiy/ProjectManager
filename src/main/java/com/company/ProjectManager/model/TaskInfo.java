package com.company.ProjectManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TaskInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String task;

    @JsonIgnore
    private Boolean isDeleted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectInfo project;

    public TaskInfo(Long id, String task, ProjectInfo project) {
        this.id = id;
        this.task = task;
        this.project = project;
    }
}
