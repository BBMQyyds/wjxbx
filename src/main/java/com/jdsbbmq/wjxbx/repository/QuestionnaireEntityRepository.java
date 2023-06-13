package com.jdsbbmq.wjxbx.repository;

import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "questionnaire")
public interface QuestionnaireEntityRepository extends PagingAndSortingRepository<QuestionnaireEntity, String> {
}
