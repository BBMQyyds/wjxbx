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
    public List<QuestionEntity> selectQuestionById(String questionnaireId);

    //查询个人题库中的所有问题
    public List<QuestionEntity> selectPrivateQuestion(String userId);

        /*
           增删改
        */

    //增加
    //设计问卷问题
    public int insertDesignQuestion(List<QuestionEntity> questionEntityList);

    //将问卷问题放入个人题库
    public int insertPrivateQuestion(QuestionEntity questionEntity);

    //更新


    //删除

    //将问卷中的问题删除
    public int deleteQuestionById(String questionnaire_id);

    //将个人题库中的问题删除
    public int deletePrivateQuestionById(String id);


        /*
           辅助功能
        */
    //根据所给的问题Id，查找其设计的问题内容
    public String selectQuestionContentById(String questionId);

    //更新问题的收藏状态
    public int updateStarOnQuestion(String questionId);
    //取消问题的收藏状态
    public int updateStarOffQuestion(String questionId);

    //删除列表中没出现的问题ID
    public int deleteQuestionNotInList(List<QuestionEntity> questionEntityList);

    public int updateQuestionnaireQuestions(List<QuestionEntity> questionEntityList);


}
