package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.TaskInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskInfo, Long> {

    List<TaskInfo> findTaskInfoByProjectIdAndIsDeleted(Long projectInfo, Boolean isDeleted, Pageable pageable);
    List<TaskInfo> findTaskInfoByProjectIdAndIsDeletedAndTaskContains(Long projectInfo, Boolean isDeleted,String task, Pageable pageable);
    TaskInfo getByIdAndIsDeleted(Long id, Boolean isDeleted);
}
