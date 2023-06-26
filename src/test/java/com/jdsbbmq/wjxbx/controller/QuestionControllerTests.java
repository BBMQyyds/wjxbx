package com.jdsbbmq.wjxbx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.bean.question.Question;
import com.jdsbbmq.wjxbx.bean.question.UpdateQuestionStarRequest;
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

import java.util.ArrayList;

@AutoConfigureMockMvc
@SpringBootTest
public class QuestionControllerTests {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    // 查询所有问卷
    public void selectQuestionByIdTest() throws Exception {
        String questionId = "1d2489bc-5da0-43ff-9a7c-7533c0a59e99";
        mockMvc.perform(MockMvcRequestBuilders.post("/selectQuestionById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>selectQuestionById问卷查询测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>selectQuestionById问卷查询测试失败");
                    }
                });
    }

    @Test
    // 查询私人题库
    public void selectPrivateQuestionTest() throws Exception {
        String userId = "53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f";
        mockMvc.perform(MockMvcRequestBuilders.post("/selectPrivateQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>selectPrivateQuestionById私人题库查询测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>selectPrivateQuestionById私人题库查询测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //设计问卷问题
    public void insertDesignQuestionTest() throws Exception {
        DesignRequest designRequest=new DesignRequest("1d2489bc-5da0-43ff-9a7c-7533c0a59e99",new ArrayList<Question>());
        String jsonProject = new ObjectMapper().writeValueAsString(designRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/insertDesignQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>insertDesignQuestion设计问卷问题测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>insertDesignQuestion设计问卷问题测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //将问卷问题放入个人题库接口
    public void insertPrivateQuestionTest() throws Exception {
        UpdateQuestionStarRequest updateQuestionStarRequest=new UpdateQuestionStarRequest("53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f","1d2489bc-5da0-43ff-9a7c-7533c0a59e99");
        String jsonProject = new ObjectMapper().writeValueAsString(updateQuestionStarRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/insertPrivateQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>insertPrivateQuestion将问卷问题放入个人题库接口测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>insertPrivateQuestion将问卷问题放入个人题库接口测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //删除问卷问题接口
    public void deleteQuestionTest() throws Exception {
        String questionId = "1d2489bc-5da0-43ff-9a7c-7533c0a59e99";
        mockMvc.perform(MockMvcRequestBuilders.post("/deletePrivateQuestionById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>deleteQuestionById删除问卷题库问题接口测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>deleteQuestionById删除问卷题库问题接口测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //清空回收站
    public void deleteAllQuestionnaireRecycledTest() throws Exception {
        String userId = "53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f";
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteAllQuestionnaireRecycled")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>deleteAllQuestionnaireRecycled清空回收站测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>deleteAllQuestionnaireRecycled清空回收站测试失败");
                    }
                });
    }
}
