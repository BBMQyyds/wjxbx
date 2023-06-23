package com.jdsbbmq.wjxbx.service.Impl;

import com.google.gson.Gson;
import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.dao.QuestionEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.QuestionEntity;
import com.jdsbbmq.wjxbx.service.QuestionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionEntityMapper questionEntityMapper;

    @Override
    public int insertDesignQuestion(DesignRequest designRequest) {
        Gson gson=new Gson();
        List<QuestionEntity> questionEntityList = new ArrayList<>();
        for (int i = 0; i < designRequest.getQuestions().size(); i++) {
           QuestionEntity questionEntity=new QuestionEntity(designRequest.getId(),i,gson.toJson(designRequest.getQuestions().get(i)));
           questionEntityList.add(questionEntity);
        }
        return questionEntityMapper.insertDesignQuestion(questionEntityList);
    }
}
