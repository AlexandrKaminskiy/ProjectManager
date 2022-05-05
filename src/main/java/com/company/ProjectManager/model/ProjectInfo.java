package com.company.ProjectManager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
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

}