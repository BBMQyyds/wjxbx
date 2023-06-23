package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface QuestionEntityMapper {
        /*
           查询
        */


        /*
           增删改
        */

    //增加
    public int insertDesignQuestion(List<QuestionEntity> questionEntityList);
}
