package com.company.ProjectManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @JsonIgnore
    private Boolean isDeleted;
    private String companyName;
    private Boolean isReady;
    @ManyToMany
    @JoinColumn(referencedColumnName = "author_id")
    private List<User> author;
}