package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.ProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProjectInfoRepo extends CrudRepository<ProjectInfo, Long> {

}
