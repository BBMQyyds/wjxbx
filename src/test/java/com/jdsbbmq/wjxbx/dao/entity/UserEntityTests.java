package com.jdsbbmq.wjxbx.dao.entity;

import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserEntityTests {

    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {

    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        UserEntity userEntity = new UserEntity("1", "2", "3", new Date(), new Date(), 0, "4", new Date(), "5", new Date());
        userEntity.setStopTime(new Date());
        if (userEntity == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("UserEntity: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(userEntity);
            // 记录info级别的信息
            log.info("UserEntity: >>allArgsConstructor全参构造测试成功");
        }
    }

    @Test
    //测试复制时User为null时的构造方法
    public void copyConstructorTest1() {
        UserEntity userEntity = new UserEntity(null);
        if (userEntity == null) {
            System.out.println("复制时User为null时的构造方法失败");
            // 记录error级别的信息
            log.error("UserEntity: >>copyConstructor复制时User为null时的构造方法测试失败");
        } else {
            System.out.println(userEntity);
            // 记录info级别的信息
            log.info("UserEntity: >>copyConstructor复制时User为null时的构造方法测试成功");
        }
    }
}
