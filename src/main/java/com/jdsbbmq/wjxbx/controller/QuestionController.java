package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.answer.AnswerRequest;
import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.bean.question.Question;
import com.jdsbbmq.wjxbx.bean.question.UpdateQuestionStarRequest;
import com.jdsbbmq.wjxbx.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @Resource
    @Qualifier("asyncServiceExecutor")
    Executor executor;

    /*
        查询
     */
    //根据所给的问卷Id，查找其设计的问题
    @Operation(summary = "根据所给的问卷Id，查找其设计的问题接口", description = "根据所给的问卷Id，查找其设计的问题")
    @RequestMapping(value = "/selectQuestionById", method = RequestMethod.POST)
    public List<Question> selectQuestionById(@RequestBody String id) {
        CompletableFuture<List<Question>> questions = CompletableFuture.supplyAsync(() ->
                questionService.selectQuestionById(id), executor);
        return questions.join();
    }

    //查询个人题库中的所有问题
    @Operation(summary = "查询个人题库中的所有问题接口", description = "查询个人题库中的所有问题")
    @RequestMapping(value = "/selectPrivateQuestion", method = RequestMethod.POST)
    public List<Question> selectPrivateQuestion(@RequestBody String userId) {
        CompletableFuture<List<Question>> questions = CompletableFuture.supplyAsync(() ->
                questionService.selectPrivateQuestion(userId), executor);
        return questions.join();
    }


    /*
        增删改
     */

    //增加

    //设计问卷问题
    @Operation(summary = "设计问卷问题接口", description = "用户可以设计问卷问题")
    @RequestMapping(value = "/insertDesignQuestion", method = RequestMethod.POST)
    public Integer insertDesignQuestion(@RequestBody DesignRequest designRequest) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() ->
                questionService.insertDesignQuestion(designRequest), executor);
        return integerCompletableFuture.join();
    }

    //增加答卷
    @Operation(summary = "增加答卷", description = "增加答卷")
    @RequestMapping(value = "/insertAnswer", method = RequestMethod.POST)
    public Integer insertAnswer(@RequestBody AnswerRequest answerRequest) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() ->
                questionService.insertAnswer(answerRequest), executor);
        return integerCompletableFuture.join();
    }

    //将问卷问题放入个人题库
    @Operation(summary = "将问卷问题放入个人题库接口", description = "将问卷问题放入个人题库")
    @RequestMapping(value = "/insertPrivateQuestion", method = RequestMethod.POST)
    public Integer insertPrivateQuestion(@RequestBody UpdateQuestionStarRequest updateQuestionStarRequest) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() ->
                questionService.insertPrivateQuestion(updateQuestionStarRequest), executor);
        return integerCompletableFuture.join();
    }

    //删除
    //将问卷问题拿出个人题库
    @Operation(summary = "将问卷问题拿出个人题库接口", description = "将问卷问题拿出个人题库")
    @RequestMapping(value = "/deletePrivateQuestionById", method = RequestMethod.POST)
    public Integer deletePrivateQuestionById(@RequestBody String questionId) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() ->
                questionService.deletePrivateQuestionById(questionId), executor);
        return integerCompletableFuture.join();
    }
}
