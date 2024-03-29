package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.dao.ProjectEntityMapper;
import com.jdsbbmq.wjxbx.dao.QuestionEntityMapper;
import com.jdsbbmq.wjxbx.dao.QuestionnaireEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.QuestionEntity;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import com.jdsbbmq.wjxbx.service.QuestionnaireService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Resource
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    @Resource
    private ProjectEntityMapper projectEntityMapper;

    @Resource
    private QuestionEntityMapper questionEntityMapper;

    //查找一个项目下的所有问卷
    @Override
    public List<Questionnaire> selectAllQuestionnaire(String projectId) throws ParseException {
        List<QuestionnaireEntity> listQuestionnaireEntity = questionnaireEntityMapper.selectAllQuestionnaire(projectId);
        List<Questionnaire> listQuestionnaire = new ArrayList<>();
        for (QuestionnaireEntity questionnaireEntity : listQuestionnaireEntity) {
            Questionnaire questionnaire = new Questionnaire(questionnaireEntity);
            listQuestionnaire.add(questionnaire);
        }
        return listQuestionnaire;
    }

    //查找一个问卷
    @Override
    public Questionnaire selectQuestionnaireById(String questionnaireId) throws ParseException {
        return new Questionnaire(questionnaireEntityMapper.selectQuestionnaireById(questionnaireId));
    }

    //分页查找问卷
    @Override
    public List<Questionnaire> selectQuestionnaireByPage(QueryRequest queryRequest) throws ParseException {
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
        List<QuestionnaireEntity> listQuestionnaireEntity = questionnaireEntityMapper.selectQuestionnaireByPage(queryRequest.ToQueryEntity());
        List<Questionnaire> listQuestionnaire = new ArrayList<>();
        for (QuestionnaireEntity questionnaireEntity : listQuestionnaireEntity) {
            Questionnaire questionnaire = new Questionnaire(questionnaireEntity);
            listQuestionnaire.add(questionnaire);
        }
        return listQuestionnaire;
    }

    //增删改

    //增加

    //插入一个问卷
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Integer insertQuestionnaire(Questionnaire questionnaire) throws ParseException {
        questionnaire.init();
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity(questionnaire);
        try {
            int a = questionnaireEntityMapper.insertQuestionnaire(questionnaireEntity);
            int b = projectEntityMapper.addProjectQuestionnaireCount(questionnaireEntity.getProjectId());
            if (a == 0 || b == 0) {
                throw new RuntimeException("插入问卷失败");
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("插入问卷失败");
        }
    }

    // 复制问卷
    @Override
    public Integer insertCopyQuestionnaire(String questionnaireId) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        QuestionnaireEntity questionnaireEntity = questionnaireEntityMapper.selectQuestionnaireById(questionnaireId);
        questionnaireEntity.setId(UUID.randomUUID().toString());
        questionnaireEntity.setQuestionnaireName(questionnaireEntity.getQuestionnaireName() + " - 副本");
        questionnaireEntity.setStartTime(null);
        questionnaireEntity.setEndTime(null);
        questionnaireEntity.setAnswerCount(0);
        questionnaireEntity.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));
        questionnaireEntityMapper.insertQuestionnaire(questionnaireEntity);
        projectEntityMapper.addProjectQuestionnaireCount(questionnaireEntity.getProjectId());
        for (QuestionEntity questionEntity : questionEntityMapper.selectQuestionById(questionnaireId)) {
            questionEntity.setQuestionId(UUID.randomUUID().toString());
            questionEntity.setId(questionnaireEntity.getId());
            questionEntity.setStar(0);
            questionEntityMapper.insertQuestion(questionEntity);
        }
        return 1;
    }

    //修改更新

    //更新一个问卷
    @Override
    public Integer updateQuestionnaire(Questionnaire questionnaire) {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity(questionnaire);
        return questionnaireEntityMapper.updateQuestionnaire(questionnaireEntity);
    }

    //更新问卷的收藏状态
    @Override
    public Integer updateStarOnQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateStarOnQuestionnaire(questionnaireId);
    }

    @Override
    public Integer updateStarOffQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateStarOffQuestionnaire(questionnaireId);
    }

    @Override
    public Integer updateDeletedOnQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateDeletedOnQuestionnaire(questionnaireId);
    }

    @Override
    public Integer updateDeletedOffQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateDeletedOffQuestionnaire(questionnaireId);
    }

    @Override
    public Integer updateReleaseQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateReleaseQuestionnaire(questionnaireId);
    }

    @Override
    public Integer updateReclaimQuestionnaire(String questionnaireId) {
        return questionnaireEntityMapper.updateReclaimQuestionnaire(questionnaireId);
    }

    //删除项目
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Integer deleteQuestionnaireById(String questionnaireId) {
        try {
            QuestionnaireEntity questionnaireEntity = questionnaireEntityMapper.selectQuestionnaireById(questionnaireId);
            int a = questionnaireEntityMapper.deleteQuestionnaireById(questionnaireId);
            int b = projectEntityMapper.reduceProjectQuestionnaireCount(questionnaireEntity.getProjectId());
            if (a == 0 || b == 0 || questionnaireEntity.getId() == null) {
                throw new RuntimeException("删除问卷失败");
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("删除问卷失败");
        }
    }

    //清空回收站
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer deleteAllQuestionnaireRecycled(String projectId) {
        try {
            int a = projectEntityMapper.reduceProjectQuestionnaireCountRecycled(projectId);
            int b = questionnaireEntityMapper.deleteAllQuestionnaireRecycled(projectId);
            if (a == 0 || b == 0) {
                throw new RuntimeException("清空回收站失败");
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("清空回收站失败");
        }
    }
}
