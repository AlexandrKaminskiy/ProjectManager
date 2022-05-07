package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectInfoRepo extends JpaRepository<ProjectInfo, Long> {
    List<ProjectInfo> findByAuthor(User user);
}