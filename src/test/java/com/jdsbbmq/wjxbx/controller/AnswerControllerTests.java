package com.jdsbbmq.wjxbx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdsbbmq.wjxbx.bean.QueryRequest;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
public class AnswerControllerTests {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    //查找Answer
    public void selectAnswerByPageTests() throws Exception {
        QueryRequest queryRequest = new QueryRequest();
        String jsonProject = new ObjectMapper().writeValueAsString(queryRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/selectAnswerByPage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("ProjectController: >>selectAnswerByPage所有项目查询测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("ProjectController: >>selectAnswerByPage所有项目查询测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //查询答卷总量
    public void selectAnswerCountTests() throws Exception {
        String questionnaireId="1ddf1c08-d30d-4a70-a237-5057900a1768";
        mockMvc.perform(MockMvcRequestBuilders.post("/selectAnswerCount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionnaireId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("ProjectController: >>selectAnswerCount所有项目查询测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("ProjectController: >>selectAnswerCount所有项目查询测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //根据id查询答卷
    public void selectAnswerByIdTests() throws Exception {
        String answerId="4130c9a6-bfa6-4642-8fc6-7cc0e9dd0c26";
        mockMvc.perform(MockMvcRequestBuilders.post("/selectAnswerById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(answerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("ProjectController: >>selectAllProject所有项目查询测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("ProjectController: >>selectAllProject所有项目查询测试失败");
                    }
                });
    }
}
