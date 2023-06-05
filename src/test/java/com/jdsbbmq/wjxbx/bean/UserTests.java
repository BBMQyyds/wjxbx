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
        System.out.println(user);
    }

    @Test
    //canEqual
    public void canEqualTest() {
        User user = new User();
        System.out.println(user.ca);
    }

    @Test
    //equals
    public void equalsTest() {
        User user = new User();
        System.out.println(user.equals(new User()));
    }

    @Test
    //hashcode
    public void hashcodeTest() {
        User user = new User();
        System.out.println(user.hashCode());
    }

}
