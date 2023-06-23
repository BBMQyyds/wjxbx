package com.jdsbbmq.wjxbx.service.Impl;

import com.google.gson.Gson;
import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.bean.question.Question;
import com.jdsbbmq.wjxbx.dao.QuestionEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.QuestionEntity;
import com.jdsbbmq.wjxbx.service.QuestionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionEntityMapper questionEntityMapper;

        /*
            查询
         */
    //根据所给的问卷Id，查找其设计的问题
    @Override
    public List<Question> selectQuestionById(String id) {
        Gson gson = new Gson();
        List<QuestionEntity> questionEntityList = questionEntityMapper.selectQuestionById(id);
        List<Question> questionList = new ArrayList<>();
        for (QuestionEntity questionEntity : questionEntityList) {
            Question question = gson.fromJson(questionEntity.getQuestionContent(), Question.class);
            questionList.add(question);
        }
        return questionList;
    }


        /*
            增删改
        */

    //增加

    //设计问卷问题
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int insertDesignQuestion(DesignRequest designRequest) {
        try {
            Gson gson = new Gson();
            List<QuestionEntity> questionEntityList = new ArrayList<>();
            int a = questionEntityMapper.deleteQuestionById(designRequest.getId());
            for (int i = 0; i < designRequest.getQuestions().size(); i++) {
                QuestionEntity questionEntity = new QuestionEntity(designRequest.getId(), i+1, gson.toJson(designRequest.getQuestions().get(i)));
                questionEntityList.add(questionEntity);
            }
            int b = questionEntityMapper.insertDesignQuestion(questionEntityList);
            if (a + b - 1 != questionEntityList.size()) {
                throw new RuntimeException("插入设计问卷的问题失败");
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("插入设计问卷的问题失败");
        }
    }
}
