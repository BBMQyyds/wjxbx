package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.answer.Answer;
import com.jdsbbmq.wjxbx.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class AnswerController {

    @Resource
    private AnswerService answerService;

    //分页寻找答卷
    @Operation(summary = "分页寻找答卷", description = "能分页寻找项目的答卷")
    @RequestMapping(value = "/selectAnswerByPage", method = RequestMethod.POST)
    public CompletableFuture<List<Answer>> selectQuestionnaireByPage(@RequestBody QueryRequest queryRequest) {
        return answerService.selectAnswerByPage(queryRequest);
    }

    //查询答卷总数
    @Operation(summary = "查询答卷总数", description = "能查询答卷总数")
    @RequestMapping(value = "/selectAnswerCount", method = RequestMethod.POST)
    public CompletableFuture<Integer> selectAnswerCount(@RequestBody String questionnaireId) {
        return answerService.selectAnswerCount(questionnaireId);
    }

    //根据id查询答卷
    @Operation(summary = "根据id查询答卷", description = "根据id查询答卷")
    @RequestMapping(value = "/selectAnswerById", method = RequestMethod.POST)
    public CompletableFuture<Answer> selectAnswerById(@RequestBody String answerId) {
        return answerService.selectAnswerById(answerId);
    }
}
