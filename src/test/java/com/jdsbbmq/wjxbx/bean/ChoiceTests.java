package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.choice.Choice;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class ChoiceTests {
    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        Choice choice = new Choice("1", 2);
        if (choice == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("Choice: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(choice);
            // 记录info级别的信息
            log.info("Choice: >>allArgsConstructor全参构造测试成功");
        }
    }

    @Test
    //无参构造
    public void noArgsConstructorTest() {
        Choice choice = new Choice();
        if (choice == null) {
            System.out.println("无参构造失败");
            // 记录error级别的信息
            log.error("Choice: >>noArgsConstructor无参构造测试失败");
        } else {
            System.out.println(choice);
            // 记录info级别的信息
            log.info("Choice: >>noArgsConstructor无参构造测试成功");
        }
    }

    @Test
    //测试Choice的set方法
    public void testSet() {
        Choice choice = new Choice();
        choice.setContent("1");
        choice.setSequence(2);
        if (choice == null) {
            System.out.println("set方法测试失败");
            // 记录error级别的信息
            log.error("Choice: >>set方法测试失败");
        } else {
            System.out.println(choice);
            // 记录info级别的信息
            log.info("Choice: >>set方法测试成功");
        }
    }
}
