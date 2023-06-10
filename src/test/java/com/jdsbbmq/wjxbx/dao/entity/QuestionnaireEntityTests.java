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
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity("1", "2", "3", "4", new Date(), new Date(), new Date(), "5", 0);
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
}
