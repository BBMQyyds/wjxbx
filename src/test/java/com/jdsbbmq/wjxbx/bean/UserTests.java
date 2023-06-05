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
    //equals
    public void equalsTest() {
        User user1 = new User();
        User user2 = new User("1", "2", "3", new Date(), new Date(),
                0, "4", new Date(), "5", new Date());
        User user3 = new User("6", "7", "8", null, new Date(),
                0, "9", null, "10", new Date());
        User user4 = null;
        String user5 = "11";

        // 对象与自身比较
        System.out.println(user1.equals(user1));

        // 对象与相等对象比较
        System.out.println(user1.equals(user2));

        // 对象与不相等对象比较
        System.out.println(user1.equals(user3));

        // 对象与null比较
        System.out.println(user1.equals(user4));

        // 对象与其他类型的对象比较
        System.out.println(user1.equals(user5));
    }

    @Test
    //hashcode
    public void hashcodeTest() {
        User user = new User();
        User user1 = new User("1", "2", "3", new Date(), new Date(),
                0, "4", new Date(), "5", new Date());
        System.out.println(user.hashCode());
        System.out.println(user1.hashCode());
    }

}
