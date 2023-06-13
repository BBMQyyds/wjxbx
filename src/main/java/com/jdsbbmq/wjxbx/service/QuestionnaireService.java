package com.jdsbbmq.wjxbx.service;

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

     /*
        增删改
     */
    //插入一个问卷
    public int insertQuestionnaire(QuestionnaireEntity questionnaireEntity);

    //更新一个问卷
    public int updateQuestionnaire(QuestionnaireEntity questionnaireEntity);

    //删除一个问卷
    public int deleteQuestionnaireById(String questionnaireId);
}
