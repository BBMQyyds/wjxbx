package com.jdsbbmq.wjxbx.dao.entity;

import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class UserEntityTests {

    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {

    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        UserEntity userEntity = new UserEntity("1", "2", "3", new Date(), new Date(), 0, "4", new Date(), "5", new Date());
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
    //canEqual
    public void canEqualTest() {
        UserEntity userEntity = new UserEntity();
        if (userEntity.canEqual(new UserEntity())) {
            System.out.println("canEqual测试成功");
            // 记录info级别的信息
            log.info("UserEntity: >>canEqual测试成功");
        } else {
            System.out.println("canEqual测试失败");
            // 记录error级别的信息
            log.error("UserEntity: >>canEqual测试失败");
        }
    }

    @Test
    //equals
    public void equalsTest() {
        UserEntity userEntity = new UserEntity();
        if (userEntity.equals(new UserEntity())) {
            System.out.println("equals测试成功");
            // 记录info级别的信息
            log.info("UserEntity: >>equals测试成功");
        } else {
            System.out.println("equals测试失败");
            // 记录error级别的信息
            log.error("UserEntity: >>equals测试失败");
        }
    }

    @Test
    //hashcode
    public void hashcodeTest() {
        UserEntity userEntity = new UserEntity();
        if (userEntity.hashCode() == new UserEntity().hashCode()) {
            System.out.println("hashcode测试成功");
            // 记录info级别的信息
            log.info("UserEntity: >>hashcode测试成功");
        } else {
            System.out.println("hashcode测试失败");
            // 记录error级别的信息
            log.error("UserEntity: >>hashcode测试失败");
        }
    }
}
