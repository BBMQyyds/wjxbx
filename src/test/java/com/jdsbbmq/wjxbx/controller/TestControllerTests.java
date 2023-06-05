package com.jdsbbmq.wjxbx.controller;

import jakarta.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestControllerTests {

    @Resource
    private TestController testController;

    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    public void testTest() {
        testController.test("卜部美琴");
        // 记录info级别的信息
        log.info("TestController: >>test测试成功");
    }
}
