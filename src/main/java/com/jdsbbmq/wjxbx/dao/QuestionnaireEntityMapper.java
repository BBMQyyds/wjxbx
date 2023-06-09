package com.jdsbbmq.wjxbx.dao;
import java.util.List;
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


    //增删改

    // 插入问卷
    int insertQuestionnaire(QuestionnaireEntity questionnaireEntity);

    //更新问卷
    int updateQuestionnaire(QuestionnaireEntity questionnaireEntity);

    // 删除问卷
    int deleteQuestionnaireById(String id);


}
