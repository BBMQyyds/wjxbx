package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.service.QuestionnaireService;
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
public class QuestionnaireController {
    @Resource
    private QuestionnaireService questionnaireService;

    /*
        查询
     */
    //查找一个项目下的所有问卷
    @Operation(summary = "一个项目下的所有问卷", description = "查找一个项目下的所有问卷")
    @RequestMapping(value = "/selectAllQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<List<Questionnaire>> selectAllQuestionnaire(@RequestBody String projectId) throws ParseException {
        return questionnaireService.selectAllQuestionnaire(projectId);
    }

    //查找一个问卷
    @Operation(summary = "查找一个问卷", description = "根据id号查找一个问卷")
    @RequestMapping(value = "/selectQuestionnaireById", method = RequestMethod.POST)
    public CompletableFuture<Questionnaire> selectQuestionnaireById(@RequestBody String questionnaireId) throws ParseException {
        return questionnaireService.selectQuestionnaireById(questionnaireId);
    }

    //分页寻找问卷
    @Operation(summary = "分页寻找问卷", description = "能为用户分页寻找他的问卷")
    @RequestMapping(value = "/selectQuestionnaireByPage", method = RequestMethod.POST)
    public CompletableFuture<List<Questionnaire>> selectQuestionnaireByPage(@RequestBody QueryRequest queryRequest) throws ParseException {
        return questionnaireService.selectQuestionnaireByPage(queryRequest);
    }

     /*
        增删改
     */

    //插入一个问卷
    @Operation(summary = "插入一个问卷", description = "插入一个问卷")
    @RequestMapping(value = "/insertQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> insertQuestionnaire(@RequestBody Questionnaire questionnaire) throws ParseException {
        return questionnaireService.insertQuestionnaire(questionnaire);
    }

    //复制一个问卷
    @Operation(summary = "复制一个问卷", description = "复制一个问卷")
    @RequestMapping(value = "/insertCopyQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> insertCopyQuestionnaire(@RequestBody String questionnaireId) throws ParseException {
        return questionnaireService.insertCopyQuestionnaire(questionnaireId);
    }

    //更新一个问卷
    @Operation(summary = "更新一个问卷", description = "更新一个问卷")
    @RequestMapping(value = "/updateQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateQuestionnaire(@RequestBody Questionnaire questionnaire) {
        return questionnaireService.updateQuestionnaire(questionnaire);
    }

    //更新一个问卷的收藏状态
    @Operation(summary = "更新一个问卷收藏状态", description = "更新一个问卷的收藏状态")
    @RequestMapping(value = "/updateStarOnQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateStarOnQuestionnaire(@RequestBody String questionnaireId) {
        return questionnaireService.updateStarOnQuestionnaire(questionnaireId);
    }

    //更新取消一个问卷的收藏状态
    @Operation(summary = "更新取消一个问卷收藏状态", description = "更新取消一个问卷的收藏状态")
    @RequestMapping(value = "/updateStarOffQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateStarOffQuestionnaire(@RequestBody String questionnaireId) {
        return questionnaireService.updateStarOffQuestionnaire(questionnaireId);
    }

    //更新一个问卷的删除状态
    @Operation(summary = "更新一个问卷的删除状态", description = "更新一个问卷的删除状态")
    @RequestMapping(value = "/updateDeletedOnQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateDeletedOnQuestionnaire(@RequestBody String questionnaireId) {
        return questionnaireService.updateDeletedOnQuestionnaire(questionnaireId);
    }

    //更新取消一个问卷的删除状态
    @Operation(summary = "更新取消一个问卷的删除状态", description = "更新取消一个问卷的删除状态")
    @RequestMapping(value = "/updateDeletedOffQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateDeletedOffQuestionnaire(@RequestBody String questionnaireId) {
        return questionnaireService.updateDeletedOffQuestionnaire(questionnaireId);
    }

    //更新一个问卷的发布状态
    @Operation(summary = "发布一个问卷", description = "将一个问卷的状态变为发布中")
    @RequestMapping(value = "/updateReleaseQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateReleaseQuestionnaire(@RequestBody String questionnaireId) {
        return questionnaireService.updateReleaseQuestionnaire(questionnaireId);
    }

    //更新一个问卷的收回状态
    @Operation(summary = "收回一个问卷", description = "将一个问卷的状态变为收回中")
    @RequestMapping(value = "/updateReclaimQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateReclaimQuestionnaire(@RequestBody String questionnaireId) {
        return questionnaireService.updateReclaimQuestionnaire(questionnaireId);
    }

    //删除

    //删除一个问卷
    @Operation(summary = "删除一个问卷", description = "删除一个问卷")
    @RequestMapping(value = "/deleteQuestionnaire", method = RequestMethod.POST)
    public CompletableFuture<Integer> deleteQuestionnaire(@RequestBody String questionnaireId) {
        return questionnaireService.deleteQuestionnaireById(questionnaireId);
    }

    //删除一个问卷根据ID
    @Operation(summary = "删除一个问卷根据ID", description = "删除一个问卷根据ID")
    @RequestMapping(value = "/deleteQuestionnaireById", method = RequestMethod.POST)
    public CompletableFuture<Integer> deleteQuestionnaireById(@RequestBody String questionnaireId) {
        return questionnaireService.deleteQuestionnaireById(questionnaireId);
    }

    //清空回收站
    @Operation(summary = "清空回收站", description = "清空回收站")
    @RequestMapping(value = "/deleteAllQuestionnaireRecycled", method = RequestMethod.POST)
    public CompletableFuture<Integer> deleteAllQuestionnaireRecycled(@RequestBody String projectId) {
        return questionnaireService.deleteAllQuestionnaireRecycled(projectId);
    }

}
