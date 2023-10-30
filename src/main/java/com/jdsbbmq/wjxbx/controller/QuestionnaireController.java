package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.service.QuestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
public class QuestionnaireController {
    @Resource
    private QuestionnaireService questionnaireService;

    @Resource
    @Qualifier("asyncServiceExecutor")
    Executor executor;

     /*
        查询
     */

    /*
        查询
     */
    //查找一个项目下的所有问卷
    @Operation(summary = "一个项目下的所有问卷", description = "查找一个项目下的所有问卷")
    @RequestMapping(value = "/selectAllQuestionnaire", method = RequestMethod.POST)
    public List<Questionnaire> selectAllQuestionnaire(@RequestBody String projectId) {
        CompletableFuture<List<Questionnaire>> questionnaires = CompletableFuture.supplyAsync(() -> {
            try {
                return questionnaireService.selectAllQuestionnaire(projectId);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return questionnaires.join();
    }

    //查找一个问卷
    @Operation(summary = "查找一个问卷", description = "根据id号查找一个问卷")
    @RequestMapping(value = "/selectQuestionnaireById", method = RequestMethod.POST)
    public Questionnaire selectQuestionnaireById(@RequestBody String questionnaireId) {
        CompletableFuture<Questionnaire> questionnaire = CompletableFuture.supplyAsync(() -> {
            try {
                return questionnaireService.selectQuestionnaireById(questionnaireId);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return questionnaire.join();
    }

    //分页寻找问卷
    @Operation(summary = "分页寻找问卷", description = "能为用户分页寻找他的问卷")
    @RequestMapping(value = "/selectQuestionnaireByPage", method = RequestMethod.POST)
    public List<Questionnaire> selectQuestionnaireByPage(@RequestBody QueryRequest queryRequest) {
        CompletableFuture<List<Questionnaire>> questionnaires = CompletableFuture.supplyAsync(() -> {
            try {
                return questionnaireService.selectQuestionnaireByPage(queryRequest);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return questionnaires.join();
    }

     /*
        增删改
     */

    //插入一个问卷
    @Operation(summary = "插入一个问卷", description = "插入一个问卷")
    @RequestMapping(value = "/insertQuestionnaire", method = RequestMethod.POST)
    public Integer insertQuestionnaire(@RequestBody Questionnaire questionnaire) {
        CompletableFuture<Integer> insertQuestionnaire = CompletableFuture.supplyAsync(() -> {
            try {
                return questionnaireService.insertQuestionnaire(questionnaire);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return insertQuestionnaire.join();
    }

    //复制一个问卷
    @Operation(summary = "复制一个问卷", description = "复制一个问卷")
    @RequestMapping(value = "/insertCopyQuestionnaire", method = RequestMethod.POST)
    public Integer insertCopyQuestionnaire(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> insertCopyQuestionnaire = CompletableFuture.supplyAsync(() -> {
            try {
                return questionnaireService.insertCopyQuestionnaire(questionnaireId);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return insertCopyQuestionnaire.join();
    }

    //更新一个问卷
    @Operation(summary = "更新一个问卷", description = "更新一个问卷")
    @RequestMapping(value = "/updateQuestionnaire", method = RequestMethod.POST)
    public Integer updateQuestionnaire(@RequestBody Questionnaire questionnaire) {
        CompletableFuture<Integer> updateQuestionnaire = CompletableFuture.supplyAsync(() ->
                questionnaireService.updateQuestionnaire(questionnaire), executor);
        return updateQuestionnaire.join();
    }

    //更新一个问卷的收藏状态
    @Operation(summary = "更新一个问卷收藏状态", description = "更新一个问卷的收藏状态")
    @RequestMapping(value = "/updateStarOnQuestionnaire", method = RequestMethod.POST)
    public Integer updateStarOnQuestionnaire(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> updateStarOnQuestionnaire = CompletableFuture.supplyAsync(() ->
                questionnaireService.updateStarOnQuestionnaire(questionnaireId), executor);
        return updateStarOnQuestionnaire.join();
    }

    //更新取消一个问卷的收藏状态
    @Operation(summary = "更新取消一个问卷收藏状态", description = "更新取消一个问卷的收藏状态")
    @RequestMapping(value = "/updateStarOffQuestionnaire", method = RequestMethod.POST)
    public Integer updateStarOffQuestionnaire(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> updateStarOffQuestionnaire = CompletableFuture.supplyAsync(() ->
                questionnaireService.updateStarOffQuestionnaire(questionnaireId), executor);
        return updateStarOffQuestionnaire.join();
    }

    //更新一个问卷的删除状态
    @Operation(summary = "更新一个问卷的删除状态", description = "更新一个问卷的删除状态")
    @RequestMapping(value = "/updateDeletedOnQuestionnaire", method = RequestMethod.POST)
    public Integer updateDeletedOnQuestionnaire(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> updateDeletedOnQuestionnaire = CompletableFuture.supplyAsync(() ->
                questionnaireService.updateDeletedOnQuestionnaire(questionnaireId), executor);
        return updateDeletedOnQuestionnaire.join();
    }

    //更新取消一个问卷的删除状态
    @Operation(summary = "更新取消一个问卷的删除状态", description = "更新取消一个问卷的删除状态")
    @RequestMapping(value = "/updateDeletedOffQuestionnaire", method = RequestMethod.POST)
    public Integer updateDeletedOffQuestionnaire(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> updateDeletedOffQuestionnaire = CompletableFuture.supplyAsync(() ->
                questionnaireService.updateDeletedOffQuestionnaire(questionnaireId), executor);
        return updateDeletedOffQuestionnaire.join();
    }

    //更新一个问卷的发布状态
    @Operation(summary = "发布一个问卷", description = "将一个问卷的状态变为发布中")
    @RequestMapping(value = "/updateReleaseQuestionnaire", method = RequestMethod.POST)
    public Integer updateReleaseQuestionnaire(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> updateReleaseQuestionnaire = CompletableFuture.supplyAsync(() ->
                questionnaireService.updateReleaseQuestionnaire(questionnaireId), executor);
        return updateReleaseQuestionnaire.join();
    }

    //更新一个问卷的收回状态
    @Operation(summary = "收回一个问卷", description = "将一个问卷的状态变为收回中")
    @RequestMapping(value = "/updateReclaimQuestionnaire", method = RequestMethod.POST)
    public Integer updateReclaimQuestionnaire(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> updateReclaimQuestionnaire = CompletableFuture.supplyAsync(() ->
                questionnaireService.updateReclaimQuestionnaire(questionnaireId), executor);
        return updateReclaimQuestionnaire.join();
    }

    //删除

    //删除一个问卷
    @Operation(summary = "删除一个问卷", description = "删除一个问卷")
    @RequestMapping(value = "/deleteQuestionnaire", method = RequestMethod.POST)
    public Integer deleteQuestionnaire(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> deleteQuestionnaire = CompletableFuture.supplyAsync(() ->
                questionnaireService.deleteQuestionnaireById(questionnaireId), executor);
        return deleteQuestionnaire.join();
    }

    //删除一个问卷根据ID
    @Operation(summary = "删除一个问卷根据ID", description = "删除一个问卷根据ID")
    @RequestMapping(value = "/deleteQuestionnaireById", method = RequestMethod.POST)
    public Integer deleteQuestionnaireById(@RequestBody String questionnaireId) {
        CompletableFuture<Integer> deleteQuestionnaireById = CompletableFuture.supplyAsync(() ->
                questionnaireService.deleteQuestionnaireById(questionnaireId), executor);
        return deleteQuestionnaireById.join();
    }

    //清空回收站
    @Operation(summary = "清空回收站", description = "清空回收站")
    @RequestMapping(value = "/deleteAllQuestionnaireRecycled", method = RequestMethod.POST)
    public Integer deleteAllQuestionnaireRecycled(@RequestBody String projectId) {
        CompletableFuture<Integer> deleteAllQuestionnaireRecycled = CompletableFuture.supplyAsync(() ->
                questionnaireService.deleteAllQuestionnaireRecycled(projectId), executor);
        return deleteAllQuestionnaireRecycled.join();
    }

}
