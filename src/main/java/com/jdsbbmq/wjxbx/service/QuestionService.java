package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.answer.AnswerRequest;
import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.bean.question.Question;
import com.jdsbbmq.wjxbx.bean.question.UpdateQuestionStarRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface QuestionService {
    /*
        查询
     */
    //根据所给的问卷Id，查找其设计的问题
    public CompletableFuture<List<Question>> selectQuestionById(String id);

    //查询个人题库中的所有问题
    public CompletableFuture<List<Question>> selectPrivateQuestion(String userId);

    /*
        增删改
     */

    //增加

    //设计问卷问题
    public CompletableFuture<Integer> insertDesignQuestion(DesignRequest designRequest);

    //增加答卷
    public CompletableFuture<Integer> insertAnswer(AnswerRequest answerRequest);

    //将问卷问题放入个人题库
    public CompletableFuture<Integer> insertPrivateQuestion(UpdateQuestionStarRequest updateQuestionStarRequest);

    //删除
    //将问卷问题从个人题库中拿出
    public CompletableFuture<Integer> deletePrivateQuestionById(String questionId);
}
