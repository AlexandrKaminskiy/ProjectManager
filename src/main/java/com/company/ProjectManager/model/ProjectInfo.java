package com.company.ProjectManager.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Table
public class ProjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public ProjectInfo() {
    }

    public ProjectInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}