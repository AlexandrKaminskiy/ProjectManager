package com.company.ProjectManager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class ProjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany
    @JoinColumn(referencedColumnName = "user_id")
    private List<User> author;

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