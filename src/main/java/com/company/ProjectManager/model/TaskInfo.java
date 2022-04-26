package com.company.ProjectManager.model;

import javax.persistence.*;

@Entity
@Table(name = "taskinfo")
public class TaskInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String task;

}
