package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.question.Question;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class QuestionTests {
    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        Question question = new Question("1", "2", "3", new ArrayList<String>(), "4", "5", "6", 5,null,null,"6");
        if (question == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("Question: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(question);
            // 记录info级别的信息
            log.info("Question: >>allArgsConstructor全参构造测试成功");
        }
    }


    @Test
    //无参构造
    public void noArgsConstructorTest() {
        Question question = new Question();
        if (question == null) {
            System.out.println("无参构造失败");
            // 记录error级别的信息
            log.error("Question: >>noArgsConstructor无参构造测试失败");
        } else {
            System.out.println(question);
            // 记录info级别的信息
            log.info("Question: >>noArgsConstructor无参构造测试成功");
        }
    }

    @Test
    //测试Question的set方法
    public void testSet() {
        Question question = new Question();
        question.setType("1");
        question.setStar(0);
        question.setFormat("2");
        question.setQuestionId("3");
        question.setStem("4");
        question.setRelated("5");
        question.setOptions(new ArrayList<String>());
        if (question == null) {
            System.out.println("set方法测试失败");
            // 记录error级别的信息
            log.error("Question: >>set方法测试失败");
        } else {
            System.out.println(question);
            // 记录info级别的信息
            log.info("Question: >>set方法测试成功");
        }
    }
}
