package com.jdsbbmq.wjxbx.repository;

import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "project")
public interface ProjectEntityRepository extends PagingAndSortingRepository<ProjectEntity, String> {

}
