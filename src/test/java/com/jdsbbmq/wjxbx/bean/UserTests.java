package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.user.User;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
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

}
