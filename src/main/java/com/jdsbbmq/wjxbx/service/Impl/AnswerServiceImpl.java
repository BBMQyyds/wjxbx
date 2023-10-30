package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.answer.Answer;
import com.jdsbbmq.wjxbx.dao.AnswerEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.AnswerEntity;
import com.jdsbbmq.wjxbx.service.AnswerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerEntityMapper answerEntityMapper;

    //分页寻找答卷
    @Override
    public List<Answer> selectAnswerByPage(QueryRequest queryRequest) {
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
        List<AnswerEntity> answerEntityList = answerEntityMapper.selectAnswerByPage(queryRequest);
        List<Answer> answerList = new ArrayList<>();
        for (AnswerEntity answerEntity : answerEntityList) {
            Answer answer = new Answer(answerEntity.getId(), answerEntity.getUserId(), answerEntity.getUsername(), answerEntity.getQuestionnaireId(), answerEntity.getQuestionnaireName(), answerEntity.getQuestionnaireContent(), answerEntity.getCreateDate());
            answerList.add(answer);
        }
        return answerList;
    }

    //查询答卷总数
    @Override
    public Integer selectAnswerCount(String questionnaireId) {
        return answerEntityMapper.selectAnswerCount(questionnaireId);
    }

    //根据id查询答卷
    @Override
    public Answer selectAnswerById(String answerId) {
        AnswerEntity answerEntity = answerEntityMapper.selectAnswerById(answerId);
        if (answerEntity == null) {
            return null;
        }
        return new Answer(answerEntity.getId(), answerEntity.getUserId(), answerEntity.getUsername(), answerEntity.getQuestionnaireId(), answerEntity.getQuestionnaireName(), answerEntity.getQuestionnaireContent(), answerEntity.getCreateDate());
    }
}
