package com.jdsbbmq.wjxbx.dao.entity;

import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ProjectEntityTests {
    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        ProjectEntity projectEntity = new ProjectEntity("1", "2", "3", "4", "5", new Date(), "6", new Date(), 0, 0, 0);
        if (projectEntity == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("Project: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(projectEntity);
            // 记录info级别的信息
            log.info("Project: >>allArgsConstructor全参构造测试成功");
        }
    }

    @Test
    //测试复制时Project为null时的构造方法
    public void copyConstructorTest1() {
        ProjectEntity projectEntity = new ProjectEntity(null);
        if (projectEntity == null) {
            System.out.println("复制时Project为null时的构造方法失败");
            // 记录error级别的信息
            log.error("Project: >>copyConstructor复制时Project为null时的构造方法测试失败");
        } else {
            System.out.println(projectEntity);
            // 记录info级别的信息
            log.info("Project: >>copyConstructor复制时Project为null时的构造方法测试成功");
        }
    }
}
