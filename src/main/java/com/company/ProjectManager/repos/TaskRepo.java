package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<TaskInfo, Long> {

    Iterable<TaskInfo> findTaskInfoByProjectId(Long projectInfo);
}
