package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.file.File;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class FileTests {
    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    //全参构造
    public void allArgsConstructorTest() {
        File file = new File("1", "2",new Date(), "3", new Date(),  "4");
        if (file == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("File: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(file);
            // 记录info级别的信息
            log.info("File: >>allArgsConstructor全参构造测试成功");
        }
    }
}
