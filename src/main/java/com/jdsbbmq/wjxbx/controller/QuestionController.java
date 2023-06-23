package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    @Resource
    private QuestionService questionService;


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


    //删除
}
