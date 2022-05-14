package com.company.ProjectManager.repos;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);

    List<User> findByProject(ProjectInfo projectInfo);

    List<User> findAll();

    @Query(value = "SELECT u FROM usr u WHERE u.?1 LIKE ?1 OR u.username LIKE ?1",nativeQuery = true)
    List<User> searchByFilter(String keyword);

}
