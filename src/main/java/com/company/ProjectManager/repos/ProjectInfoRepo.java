package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.ProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectInfoRepo extends JpaRepository<ProjectInfo, Long> {

}
