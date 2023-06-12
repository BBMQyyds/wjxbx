package com.jdsbbmq.wjxbx.repository;

import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface UserEntityRepository extends PagingAndSortingRepository<UserEntity, String> {

}
