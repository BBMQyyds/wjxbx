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
    public void ToString(){
        ChangeRequest changeRequest = new ChangeRequest();
        if(changeRequest.toString() == null) {
            //日志输入
            log.info("ChangeRequest: >>ChangeRequestToString测试失败");
        }else{
            log.info("ChangeRequest: >>ChangeRequestToString测试成功");
        }
    }
}
