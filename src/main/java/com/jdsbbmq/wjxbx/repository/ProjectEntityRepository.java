package com.jdsbbmq.wjxbx.repository;

import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RepositoryRestResource(path = "project")
public interface ProjectEntityRepository extends PagingAndSortingRepository<ProjectEntity, String> {

    @Query("SELECT p FROM project p WHERE p.projectName LIKE %:projectName%")
    Page<ProjectEntity> findByProjectNameContaining(@Param("projectName") String projectName, Pageable pageable);

    @Query("SELECT p FROM project p WHERE p.projectName LIKE %:projectName%")
    List<ProjectEntity> findByProjectName(@Param("projectName") String projectName);
}
