package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QuestionnaireService {
    /*
        查询
     */

    //查找一个项目下的所有问卷
    public List<QuestionnaireEntity> selectAllQuestionnaire(String projectId);

    //查找一个问卷
    public QuestionnaireEntity selectQuestionnaireById(String questionnaireId);

    //分页查找问卷
    public List<Questionnaire> selectQuestionnaireByPage(QueryRequest queryRequest);

     /*
        增删改
     */

    //增加

    //插入一个问卷
    public int insertQuestionnaire(QuestionnaireEntity questionnaireEntity);

    //修改更新

    //更新一个问卷
    public int updateQuestionnaire(QuestionnaireEntity questionnaireEntity);

    //更新问卷的收藏状态
    public int updateStarOnQuestionnaire(String questionnaireId);

    //更新取消问卷的收藏状态
    public int updateStarOffQuestionnaire(String questionnaireId);

    //更新问卷的删除状态
    public int updateDeletedOnQuestionnaire(String questionnaireId);

    //更新取消问卷的删除状态
    public int updateDeletedOffQuestionnaire(String questionnaireId);

    //删除一个问卷
    public int deleteQuestionnaireById(String questionnaireId);
}
