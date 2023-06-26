package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;

@SpringBootTest
public class QuestionnaireTests {
    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        Questionnaire questionnaire = new Questionnaire("1", "2", "3", "4", new Date(), new Date(), new Date(), 0, 0, 0, 0);
        if (questionnaire == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("Questionnaire: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(questionnaire);
            // 记录info级别的信息
            log.info("Questionnaire: >>allArgsConstructor全参构造测试成功");
        }
    }

    @Test
    //测试复制时QuestionnaireEntity为null时的构造方法
    public void copyConstructorTest1() throws ParseException {
        Questionnaire questionnaire = new Questionnaire(null);
        if (questionnaire == null) {
            System.out.println("复制时QuestionnaireEntity为null时的构造方法失败");
            // 记录error级别的信息
            log.error("Questionnaire: >>copyConstructor复制时QuestionnaireEntity为null时的构造方法测试失败");
        } else {
            System.out.println(questionnaire);
            // 记录info级别的信息
            log.info("Questionnaire: >>copyConstructor复制时QuestionnaireEntity为null时的构造方法测试成功");
        }
    }

    @Test
    //   测试复制时QuestionnaireEntity不为null时的构造方法
    public void copyConstructorTest2() throws ParseException {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity("1", "2", "3", "4", new Date(), new Date(), new Date(), 0, 0, 0, 0);
        Questionnaire questionnaire = new Questionnaire(questionnaireEntity);
        if (questionnaire == null) {
            System.out.println("复制时QuestionnaireEntity不为null时的构造方法失败");
            // 记录error级别的信息
            log.error("Questionnaire: >>copyConstructor复制时QuestionnaireEntity不为null时的构造方法测试失败");
        } else {
            System.out.println(questionnaire);
            // 记录info级别的信息
            log.info("Questionnaire: >>copyConstructor复制时QuestionnaireEntity不为null时的构造方法测试成功");
        }
    }

    @Test
    //测试复制时Questionnaire的Set方法
    public void setTest1() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("1");
        questionnaire.setQuestionnaireName("2");
        questionnaire.setQuestionnaireDescription("3");
        questionnaire.setProjectId("4");
        questionnaire.setCreationDate(new Date());
        questionnaire.setEndTime(new Date());
        questionnaire.setQuestionCount(0);
        questionnaire.setAnswerCount(0);
        questionnaire.setStar(0);
        questionnaire.setStartTime(new Date());
        if (questionnaire == null) {
            System.out.println("复制时Questionnaire的Set方法失败");
            // 记录error级别的信息
            log.error("Questionnaire: >>set复制时Questionnaire的Set方法测试失败");
        } else {
            System.out.println(questionnaire);
            // 记录info级别的信息
            log.info("Questionnaire: >>set复制时Questionnaire的Set方法测试成功");
        }
    }
}
