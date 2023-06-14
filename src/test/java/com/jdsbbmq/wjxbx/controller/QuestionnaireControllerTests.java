package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import jakarta.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class QuestionnaireControllerTests {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Resource
    private QuestionnaireController questionnaireController;
    @Test
    void contextLoads() {

    }

    @Test
    // 查询所有问卷
    public void selectAllQuestionnaireTest(){
        String projectId="1779cd18-cc34-47b2-a453-419fcff8df3b";
        List<Questionnaire> questionnaireList= questionnaireController.selectAllQuestionnaire(projectId);
        if (CollectionUtils.isEmpty(questionnaireList)) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("QuestionnaireController: >>selectAllQuestionnaire所有问卷查询测试失败");
        } else {
            System.out.println(questionnaireList);
            // 记录info级别的信息
            log.info("QuestionnaireController: >>selectAllQuestionnaire所有问卷查询测试成功");
        }
    }

    @Test
    // 根据id查询问卷
    public void selectQuestionnaireByIdTest(){
        String id="1";
        Questionnaire questionnaire=questionnaireController.selectQuestionnaireById(id);
        if (questionnaire == null) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("QuestionnaireController: >>selectQuestionnaireById根据id查询问卷测试失败");
        } else {
            System.out.println(questionnaire);
            // 记录info级别的信息
            log.info("QuestionnaireController: >>selectQuestionnaireById根据id查询问卷测试成功");
        }
    }

    @Test
    @Transactional
    // 新增问卷
    public void insertQuestionnaireTest(){
        Questionnaire questionnaire = new Questionnaire("2", "1779cd18-cc34-47b2-a453-419fcff8df3b", "3", "4", new Date(),new Date(),new Date(),"5",0);
        int result=questionnaireController.insertQuestionnaire(questionnaire);
        if (result == 0) {
            System.out.println("新增失败");
            // 记录error级别的信息
            log.error("QuestionnaireController: >>insertQuestionnaire新增问卷测试失败");
        } else {
            System.out.println("新增成功");
            // 记录info级别的信息
            log.info("QuestionnaireController: >>insertQuestionnaire新增问卷测试成功");
        }
    }

    @Test
    @Transactional
    // 更新问卷
    public void updateQuestionnaireTest(){
        Questionnaire questionnaire = new Questionnaire("1", "1779cd18-cc34-47b2-a453-419fcff8df3b", "3", "4", new Date(),new Date(),new Date(),"5",0);
        int result=questionnaireController.updateQuestionnaire(questionnaire);
        if (result == 0) {
            System.out.println("更新失败");
            // 记录error级别的信息
            log.error("QuestionnaireController: >>updateQuestionnaire更新问卷测试失败");
        } else {
            System.out.println("更新成功");
            // 记录info级别的信息
            log.info("QuestionnaireController: >>updateQuestionnaire更新问卷测试成功");
        }
    }

    @Test
    @Transactional
    // 删除问卷
    public void deleteQuestionnaireTest(){
        String id="4173bc9e-1a42-4747-97e2-775f3ac88714";
        int result=questionnaireController.deleteQuestionnaire(id);
        if (result == 0) {
            System.out.println("删除失败");
            // 记录error级别的信息
            log.error("QuestionnaireController: >>deleteQuestionnaire删除问卷测试失败");
        } else {
            System.out.println("删除成功");
            // 记录info级别的信息
            log.info("QuestionnaireController: >>deleteQuestionnaire删除问卷测试成功");
        }
    }
}
