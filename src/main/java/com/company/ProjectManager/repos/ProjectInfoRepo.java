package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectInfoRepo extends JpaRepository<ProjectInfo, Long> {

    List<ProjectInfo> findByAuthorAndIsDeleted(User user, Boolean isDeleted, Pageable pageable);
    ProjectInfo findByIdAndIsDeleted(Long id, Boolean isDeleted);
    List<ProjectInfo> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    List<ProjectInfo> findByIsDeletedAndCompanyNameContainsAndNameContainsAndAuthor(Boolean isDeleted,
                                                                                          String companyName,
                                                                                          String name,
                                                                                          User user,
                                                                                          Pageable pageable);
    List<ProjectInfo> findByIsDeletedAndCompanyNameContainsAndNameContains(Boolean isDeleted,
                                                                                    String companyName,
                                                                                    String name,
                                                                                    Pageable pageable);
}