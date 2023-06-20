package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import com.jdsbbmq.wjxbx.service.QuestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionnaireController {
    @Resource
    private QuestionnaireService questionnaireService;

    /*
        查询
     */
    //查找一个项目下的所有问卷
    @Operation(summary = "一个项目下的所有问卷", description = "查找一个项目下的所有问卷")
    @RequestMapping(value = "/selectAllQuestionnaire" ,method = RequestMethod.POST)
    public List<Questionnaire> selectAllQuestionnaire(@RequestBody String projectId) {
        List<QuestionnaireEntity> listQuestionnaireEntity= questionnaireService.selectAllQuestionnaire(projectId);
        List<Questionnaire> listQuestionnaire=new ArrayList<>();
        for (QuestionnaireEntity questionnaireEntity:listQuestionnaireEntity){
            Questionnaire questionnaire=new Questionnaire(questionnaireEntity);
            listQuestionnaire.add(questionnaire);
        }
        return listQuestionnaire;
    }

    //查找一个问卷
    @Operation(summary = "查找一个问卷", description = "根据id号查找一个问卷")
    @RequestMapping(value = "/selectQuestionnaireById" ,method = RequestMethod.POST)
    public Questionnaire selectQuestionnaireById(@RequestBody String questionnaireId) {
        QuestionnaireEntity questionnaireEntity=questionnaireService.selectQuestionnaireById(questionnaireId);
        return new Questionnaire(questionnaireEntity);

    }

    //分页寻找问卷
    @Operation(summary = "分页寻找问卷", description = "能为用户分页寻找他的问卷")
    @RequestMapping(value = "/selectQuestionnaireByPage", method = RequestMethod.POST)
    public List<Questionnaire> selectQuestionnaireByPage(@RequestBody QueryRequest queryRequest) {
       return questionnaireService.selectQuestionnaireByPage(queryRequest);
    }

     /*
        增删改
     */

    //插入一个问卷
    @Operation(summary = "插入一个问卷", description = "插入一个问卷")
    @RequestMapping(value = "/insertQuestionnaire" ,method = RequestMethod.POST)
    public int insertQuestionnaire(@RequestBody Questionnaire questionnaire) {
        QuestionnaireEntity questionnaireEntity=new QuestionnaireEntity(questionnaire);
        return questionnaireService.insertQuestionnaire(questionnaireEntity);
    }

    //更新一个问卷
    @Operation(summary = "更新一个问卷", description = "更新一个问卷")
    @RequestMapping(value = "/updateQuestionnaire" ,method = RequestMethod.POST)
    public int updateQuestionnaire(@RequestBody Questionnaire questionnaire) {
        QuestionnaireEntity questionnaireEntity=new QuestionnaireEntity(questionnaire);
        return questionnaireService.updateQuestionnaire(questionnaireEntity);
    }

    //删除一个问卷
    @Operation(summary = "删除一个问卷", description = "删除一个问卷")
    @RequestMapping(value = "/deleteQuestionnaire" ,method = RequestMethod.POST)
    public int deleteQuestionnaire(@RequestBody String questionnaireId) {
        return questionnaireService.deleteQuestionnaireById(questionnaireId);
    }
}
