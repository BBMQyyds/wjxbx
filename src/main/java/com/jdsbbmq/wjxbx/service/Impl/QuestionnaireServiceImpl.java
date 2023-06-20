package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.dao.ProjectEntityMapper;
import com.jdsbbmq.wjxbx.dao.QuestionnaireEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import com.jdsbbmq.wjxbx.service.QuestionnaireService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Resource
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    @Resource
    private ProjectEntityMapper projectEntityMapper;

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
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
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
    @Transactional(rollbackFor = RuntimeException.class)
    public int insertQuestionnaire(Questionnaire questionnaire) {
        questionnaire.init();
        QuestionnaireEntity questionnaireEntity=new QuestionnaireEntity(questionnaire);
        try{
            int a=questionnaireEntityMapper.insertQuestionnaire(questionnaireEntity);
            int b=projectEntityMapper.addProjectQuestionnaireCount(questionnaireEntity.getProjectId());
            if(a==0||b==0){
                throw new RuntimeException("插入问卷失败");
            }
            return 1;
        }catch (Exception e){
            throw new RuntimeException("插入问卷失败");
        }
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
        return questionnaireEntityMapper.updateStarOnQuestionnaire(questionnaireId);
    }

    @Override
    public int updateStarOffQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateStarOffQuestionnaire(questionnaireId);
    }

    @Override
    public int updateDeletedOnQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateDeletedOnQuestionnaire(questionnaireId);
    }

    @Override
    public int updateDeletedOffQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateDeletedOffQuestionnaire(questionnaireId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteQuestionnaireById(String questionnaireId) {
        try {
            int a=questionnaireEntityMapper.deleteQuestionnaireById(questionnaireId);
            QuestionnaireEntity questionnaireEntity=questionnaireEntityMapper.selectQuestionnaireById(questionnaireId);
            int b=projectEntityMapper.reduceProjectQuestionnaireCount(questionnaireEntity.getProjectId());
            if(a==0||b==0||questionnaireEntity.getId()==null){
                throw new RuntimeException("删除问卷失败");
            }
            return 1;
        }catch (Exception e){
            throw new RuntimeException("删除问卷失败");
        }
    }
}
