package com.jdsbbmq.wjxbx.bean.web;

import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChangeRequestTests {
    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    //测试ToString()
    public void ToStringTest() {
        ChangeRequest changeRequest = new ChangeRequest();
        if (changeRequest.toString() == null) {
            //日志输入
            log.info("ChangeRequest: >>ChangeRequestToString测试失败");
        } else {
            log.info("ChangeRequest: >>ChangeRequestToString测试成功");
        }
    }

    @Test
    //调试Setter()方法
    public void SetterTest() {
        ChangeRequest changeRequest = new ChangeRequest();
        changeRequest.setUsername("1");
        changeRequest.setOriginPassword("2");
        changeRequest.setNewPassword("3");
        changeRequest.setLastUpdatedBy("4");
        if (changeRequest.getUsername() == null || changeRequest.getOriginPassword() == null || changeRequest.getNewPassword() == null || changeRequest.getLastUpdatedBy() == null) {
            //日志输入
            log.info("ChangeRequest: >>ChangeRequestSetter测试失败");
        } else {
            log.info("ChangeRequest: >>ChangeRequestSetter测试成功");
        }
    }
}
