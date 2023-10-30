package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface QuestionnaireService {
    /*
        查询
     */

    //查找一个项目下的所有问卷
    public List<Questionnaire> selectAllQuestionnaire(String projectId) throws ParseException;

    //查找一个问卷
    public Questionnaire selectQuestionnaireById(String questionnaireId) throws ParseException;

    //分页查找问卷
    public List<Questionnaire> selectQuestionnaireByPage(QueryRequest queryRequest) throws ParseException;

     /*
        增删改
     */

    //增加

    //插入一个问卷
    public Integer insertQuestionnaire(Questionnaire questionnaire) throws ParseException;

    //复制一个问卷
    public Integer insertCopyQuestionnaire(String questionnaireId) throws ParseException;

    //修改更新

    //更新一个问卷
    public Integer updateQuestionnaire(Questionnaire questionnaire);

    //更新问卷的收藏状态
    public Integer updateStarOnQuestionnaire(String questionnaireId);

    //更新取消问卷的收藏状态
    public Integer updateStarOffQuestionnaire(String questionnaireId);

    //更新问卷的删除状态
    public Integer updateDeletedOnQuestionnaire(String questionnaireId);

    //更新取消问卷的删除状态
    public Integer updateDeletedOffQuestionnaire(String questionnaireId);

    //发布问卷
    public Integer updateReleaseQuestionnaire(String questionnaireId);

    //收回问卷
    public Integer updateReclaimQuestionnaire(String questionnaireId);

    //删除

    //删除一个问卷
    public Integer deleteQuestionnaireById(String questionnaireId);

    //清空回收站
    public Integer deleteAllQuestionnaireRecycled(String projectId);
}
