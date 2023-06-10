package com.jdsbbmq.wjxbx.bean;

import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.controller.TestControllerTests;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class ProjectTests {
    Logger log = Logger.getLogger(TestControllerTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    //全参构造
    public void allArgsConstructorTest() throws ParseException {
        Project project=new Project("1","2","3","4","5",new Date(),"6",new Date());
        if (project == null) {
            System.out.println("全参构造失败");
            // 记录error级别的信息
            log.error("Project: >>allArgsConstructor全参构造测试失败");
        } else {
            System.out.println(project);
            // 记录info级别的信息
            log.info("Project: >>allArgsConstructor全参构造测试成功");
        }
    }
}
