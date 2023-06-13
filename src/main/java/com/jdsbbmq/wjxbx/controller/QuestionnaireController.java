package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import com.jdsbbmq.wjxbx.service.QuestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionnaireController {
    @Resource
    private QuestionnaireService questionnaireService;

    //查找一个项目下的所有问卷
    @Operation(summary = "一个项目下的所有问卷", description = "查找一个项目下的所有问卷")
    @RequestMapping(value = "/selectAllQuestionnaire" ,method = RequestMethod.POST)
    public void selectAllQuestionnaire(@RequestBody String projectId) {
        questionnaireService.selectAllQuestionnaire(projectId);
    }

    //查找一个问卷
    @Operation(summary = "查找一个问卷", description = "根据id号查找一个问卷")
    @RequestMapping(value = "/selectQuestionnaire" ,method = RequestMethod.POST)
    public void selectQuestionnaire(@RequestBody String questionnaireId) {
        questionnaireService.selectQuestionnaireById(questionnaireId);
    }

     /*
        增删改
     */

    //插入一个问卷
    @Operation(summary = "插入一个问卷", description = "插入一个问卷")
    @RequestMapping(value = "/insertQuestionnaire" ,method = RequestMethod.POST)
    public void insertQuestionnaire(@RequestBody Questionnaire questionnaire) {
        QuestionnaireEntity questionnaireEntity=new QuestionnaireEntity(questionnaire);
        questionnaireService.insertQuestionnaire(questionnaireEntity);
    }

    //更新一个问卷
    @Operation(summary = "更新一个问卷", description = "更新一个问卷")
    @RequestMapping(value = "/updateQuestionnaire" ,method = RequestMethod.POST)
    public void updateQuestionnaire(@RequestBody Questionnaire questionnaire) {
        QuestionnaireEntity questionnaireEntity=new QuestionnaireEntity(questionnaire);
        questionnaireService.updateQuestionnaire(questionnaireEntity);
    }

    //删除一个问卷
    @Operation(summary = "删除一个问卷", description = "删除一个问卷")
    @RequestMapping(value = "/deleteQuestionnaire" ,method = RequestMethod.POST)
    public void deleteQuestionnaire(@RequestBody String questionnaireId) {
        questionnaireService.deleteQuestionnaireById(questionnaireId);
    }
}
