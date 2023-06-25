package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.bean.question.Question;
import com.jdsbbmq.wjxbx.bean.question.UpdateQuestionStarRequest;
import com.jdsbbmq.wjxbx.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {
    @Resource
    private QuestionService questionService;

    /*
        查询
     */
    //根据所给的问卷Id，查找其设计的问题
    @Operation(summary = "根据所给的问卷Id，查找其设计的问题接口", description = "根据所给的问卷Id，查找其设计的问题")
    @RequestMapping(value = "/selectQuestionById", method = RequestMethod.POST)
    public List<Question> selectQuestionById(@RequestBody String id) {
        return questionService.selectQuestionById(id);
    }

    //查询个人题库中的所有问题
    @Operation(summary = "查询个人题库中的所有问题接口", description = "查询个人题库中的所有问题")
    @RequestMapping(value = "/selectPrivateQuestion", method = RequestMethod.POST)
    public List<Question> selectPrivateQuestion(@RequestBody String userId) {
        return questionService.selectPrivateQuestion(userId);
    }


    /*
        增删改
     */

    //增加

    //设计问卷问题
    @Operation(summary = "设计问卷问题接口", description = "用户可以设计问卷问题")
    @RequestMapping(value = "/insertDesignQuestion", method = RequestMethod.POST)
    public int insertDesignQuestion(@RequestBody DesignRequest designRequest) {
        return questionService.insertDesignQuestion(designRequest);
    }

    //将问卷问题放入个人题库
    @Operation(summary = "将问卷问题放入个人题库接口", description = "将问卷问题放入个人题库")
    @RequestMapping(value = "/insertPrivateQuestion", method = RequestMethod.POST)
    public int insertPrivateQuestion(@RequestBody UpdateQuestionStarRequest updateQuestionStarRequest) {
        return questionService.insertPrivateQuestion(updateQuestionStarRequest);
    }


    //删除
    //将问卷问题拿出个人题库
    @Operation(summary = "将问卷问题拿出个人题库接口", description = "将问卷问题拿出个人题库")
    @RequestMapping(value = "/deletePrivateQuestionById", method = RequestMethod.POST)
    public int deletePrivateQuestionById(@RequestBody String questionId) {
        return questionService.deletePrivateQuestionById(questionId);
    }
}
