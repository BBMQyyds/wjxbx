package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.choice.Choice;
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
//    全参构造
    public void allArgsConstructorTest() {
        Question problem = new Question("1","2",3,new ArrayList<Choice>());
        if (problem == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("Question: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(problem);
            // 记录info级别的信息
            log.info("Question: >>allArgsConstructor全参构造测试成功");
        }
    }

//    无参构造
    @Test
    public void noArgsConstructorTest() {
        Question problem = new Question();
        if (problem == null) {
            System.out.println("无参构造失败");
            // 记录error级别的信息
            log.error("Question: >>noArgsConstructor无参构造测试失败");
        } else {
            System.out.println(problem);
            // 记录info级别的信息
            log.info("Question: >>noArgsConstructor无参构造测试成功");
        }
    }
}
