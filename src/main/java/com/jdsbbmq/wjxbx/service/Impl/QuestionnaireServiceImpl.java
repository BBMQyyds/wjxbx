package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.dao.QuestionnaireEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import com.jdsbbmq.wjxbx.service.QuestionnaireService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Resource
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    //查找一个项目下的所有问卷
    @Override
    public List<QuestionnaireEntity> selectAllQuestionnaire(String projectId) {
        return questionnaireEntityMapper.selectAllQuestionnaire(projectId);
    }

    //查找一个问卷
    @Override
    public QuestionnaireEntity selectQuestionnaireById(String questionnaireId) {
        return questionnaireEntityMapper.selectQuestionnaireById(questionnaireId);
    }

    //分页查找问卷
    @Override
    public List<Questionnaire> selectQuestionnaireByPage(QueryRequest queryRequest) {
        List<QuestionnaireEntity> listQuestionnaireEntity=questionnaireEntityMapper.selectQuestionnaireByPage(queryRequest.ToQueryEntity());
        List<Questionnaire> listQuestionnaire=new ArrayList<>();
        for (QuestionnaireEntity questionnaireEntity:listQuestionnaireEntity){
            Questionnaire questionnaire=new Questionnaire(questionnaireEntity);
            listQuestionnaire.add(questionnaire);
        }
        return listQuestionnaire;
    }

    //增删改

    //增加

    //插入一个问卷
    @Override
    public int insertQuestionnaire(QuestionnaireEntity questionnaireEntity) {
        return questionnaireEntityMapper.insertQuestionnaire(questionnaireEntity);
    }

    //修改更新

    //更新一个问卷
    @Override
    public int updateQuestionnaire(QuestionnaireEntity questionnaireEntity) {
        return questionnaireEntityMapper.updateQuestionnaire(questionnaireEntity);
    }

    //更新问卷的收藏状态
    @Override
    public int updateStarOnQuestionnaire(String questionnaireId) {
        return 0;
    }

    @Override
    public int deleteQuestionnaireById(String questionnaireId) {
        return questionnaireEntityMapper.deleteQuestionnaireById(questionnaireId);
    }
}
