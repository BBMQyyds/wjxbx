package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.user.User;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class UserTests {

    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        User user = new User("1", "2", "3", new Date(), new Date(), 0, "4", new Date(), "5", new Date());
        if (user == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("User: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(user);
            // 记录info级别的信息
            log.info("User: >>allArgsConstructor全参构造测试成功");
        }
    }

    @Test
    //canEqual
    public void canEqualTest() {
        User user = new User();
        if (user.canEqual(new User())) {
            System.out.println("canEqual测试成功");
            // 记录info级别的信息
            log.info("User: >>canEqual测试成功");
        } else {
            System.out.println("canEqual测试失败");
            // 记录error级别的信息
            log.error("User: >>canEqual测试失败");
        }
    }

    @Test
    //equals
    public void equalsTest() {
        User user = new User();
        if (user.equals(new User())) {
            System.out.println("equals测试成功");
            // 记录info级别的信息
            log.info("User: >>equals测试成功");
        } else {
            System.out.println("equals测试失败");
            // 记录error级别的信息
            log.error("User: >>equals测试失败");
        }
    }

    @Test
    //hashcode
    public void hashcodeTest() {
        User user = new User();
        if (user.hashCode() == new User().hashCode()) {
            System.out.println("hashcode测试成功");
            // 记录info级别的信息
            log.info("User: >>hashcode测试成功");
        } else {
            System.out.println("hashcode测试失败");
            // 记录error级别的信息
            log.error("User: >>hashcode测试失败");
        }
    }

}
