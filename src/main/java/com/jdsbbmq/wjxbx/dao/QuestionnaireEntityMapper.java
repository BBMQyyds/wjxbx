package com.jdsbbmq.wjxbx.dao;
import java.util.List;

import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionnaireEntityMapper {
     /*
        功能实现
     */

    //查询

    // 查询所有问卷
     List<QuestionnaireEntity> selectAllQuestionnaire(String projectId);

    // 根据id查询问卷
    QuestionnaireEntity selectQuestionnaireById(String id);

    // 分页查询问卷
    List<QuestionnaireEntity> selectQuestionnaireByPage(QueryEntity queryEntity);


    //增删改

    // 插入问卷
    int insertQuestionnaire(QuestionnaireEntity questionnaireEntity);

    //更新问卷
    int updateQuestionnaire(QuestionnaireEntity questionnaireEntity);

    //更新问卷的收藏状态
    int updateStarOnQuestionnaire(String id);

    //更新取消问卷的收藏状态
    int updateStarOffQuestionnaire(String id);

    //更新问卷的删除状态
    int updateDeletedOnQuestionnaire(String id);

    //更新取消问卷的删除状态
    int updateDeletedOffQuestionnaire(String id);

    // 删除问卷
    int deleteQuestionnaireById(String id);


}
