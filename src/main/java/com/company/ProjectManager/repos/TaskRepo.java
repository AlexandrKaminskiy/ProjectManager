package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<TaskInfo, Long> {

}
