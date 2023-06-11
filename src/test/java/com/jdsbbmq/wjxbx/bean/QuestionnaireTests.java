package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        Questionnaire questionnaire = new Questionnaire("1", "2", "3", "4", new Date(), new Date(), new Date(), "5", 0);
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
}
