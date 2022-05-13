package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<TaskInfo, Long> {

    List<TaskInfo> findTaskInfoByProjectIdAndIsDeleted(Long projectInfo,Boolean isDeleted);
    TaskInfo getByIdAndIsDeleted(Long id, Boolean isDeleted);
}
