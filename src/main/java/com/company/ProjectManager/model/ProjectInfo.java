package com.company.ProjectManager.model;

import javax.persistence.*;

@Entity
@Table(name = "projectinfo")
public class ProjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


}
