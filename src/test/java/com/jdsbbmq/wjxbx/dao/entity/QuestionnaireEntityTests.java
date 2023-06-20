package com.jdsbbmq.wjxbx.dao.entity;

import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class QuestionnaireEntityTests {
    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity("1", "2", "3", "4", new Date(), new Date(), new Date(), 0, 0, 0, 0);
        if (questionnaireEntity == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("Questionnaire: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(questionnaireEntity);
            // 记录info级别的信息
            log.info("Questionnaire: >>allArgsConstructor全参构造测试成功");
        }
    }

    @Test
    //无参构造
    public void noArgsConstructorTest() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        if (questionnaireEntity == null) {
            System.out.println("无参构造失败");
            // 记录error级别的信息
            log.error("Questionnaire: >>noArgsConstructor无参构造测试失败");
        } else {
            System.out.println(questionnaireEntity);
            // 记录info级别的信息
            log.info("Questionnaire: >>noArgsConstructor无参构造测试成功");
        }
    }

    @Test
    //测试复制时Questionnaire为null时的构造方法
    public void copyConstructorTest1() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity(null);
        if (questionnaireEntity == null) {
            System.out.println("复制时Questionnaire为null时的构造方法失败");
            // 记录error级别的信息
            log.error("QuestionnaireEntity: >>copyConstructor复制时Questionnaire为null时的构造方法测试失败");
        } else {
            System.out.println(questionnaireEntity);
            // 记录info级别的信息
            log.info("QuestionnaireEntity: >>copyConstructor复制时Questionnaire为null时的构造方法测试成功");
        }
    }

    @Test
    //测试Set方法
    public void setTest() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId("1");
        questionnaireEntity.setQuestionnaireName("2");
        questionnaireEntity.setQuestionnaireDescription("3");
        questionnaireEntity.setProjectId("4");
        questionnaireEntity.setCreationDate(new Date());
        questionnaireEntity.setEndTime(new Date());
        questionnaireEntity.setQuestionCount(0);
        questionnaireEntity.setAnswerCount(0);
        questionnaireEntity.setStar(0);
        questionnaireEntity.setStartTime(new Date());
        if (questionnaireEntity == null) {
            System.out.println("Set方法测试失败");
            // 记录error级别的信息
            log.error("QuestionnaireEntity: >>setSet方法测试失败");
        } else {
            System.out.println(questionnaireEntity);
            // 记录info级别的信息
            log.info("QuestionnaireEntity: >>setSet方法测试成功");
        }
    }
}
