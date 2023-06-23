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
    //根据所给的问卷Id，查找其设计的问题
    public List<QuestionEntity> selectQuestionById(String questionnaire_id);

        /*
           增删改
        */

    //增加
    public int insertDesignQuestion(List<QuestionEntity> questionEntityList);

    //更新


    //删除
    public int deleteQuestionById(String questionnaire_id);
}
