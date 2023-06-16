package com.jdsbbmq.wjxbx.bean.web;


import com.jdsbbmq.wjxbx.bean.user.LoginRequest;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginReuqestTests {
    Logger log = Logger.getLogger(TestControllerTests.class);
    @Test
    //测试ToString()和全参构造
    public void testToString(){
        LoginRequest loginRequest = new LoginRequest("123","123");
        if(loginRequest == null) {
            //日志输入
            log.info("LoginRequest: >>LoginRequest全参构造测试失败");
        }else{
            log.info("LoginRequest: >>LoginRequest全参构造测试成功");
        }
        System.out.println(loginRequest.toString());
        if(loginRequest.toString() == null) {
            //日志输入
            log.info("LoginRequest: >>LoginRequestToString测试失败");
        }else{
            log.info("LoginRequest: >>LoginRequestToString测试成功");
        }
    }
}
