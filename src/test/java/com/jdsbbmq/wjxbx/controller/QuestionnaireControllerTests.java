package com.jdsbbmq.wjxbx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
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

import java.util.Date;

@AutoConfigureMockMvc
@SpringBootTest
public class QuestionnaireControllerTests {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {

    }

    @Test
    // 查询所有问卷
    public void selectAllQuestionnaireTest() throws Exception {
        String projectId = "1779cd18-cc34-47b2-a453-419fcff8df3b";
        mockMvc.perform(MockMvcRequestBuilders.post("/selectAllQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>selectAllQuestionnaire所有问卷查询测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>selectAllQuestionnaire所有问卷查询测试失败");
                    }
                });
    }

    @Test
    // 根据id查询问卷
    public void selectQuestionnaireByIdTest() throws Exception {
        String id = "1";
        mockMvc.perform(MockMvcRequestBuilders.post("/selectQuestionnaireById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>selectQuestionnaireById根据id查询问卷测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>selectQuestionnaireById根据id查询问卷测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 新增问卷
    public void insertQuestionnaireTest() throws Exception {
        Questionnaire questionnaire = new Questionnaire("2", "1779cd18-cc34-47b2-a453-419fcff8df3b", "3", "4", new Date(), new Date(), new Date(), "5", 0, 0, 0);
        String jsonProject = new ObjectMapper().writeValueAsString(questionnaire);
        mockMvc.perform(MockMvcRequestBuilders.post("/insertQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>insertQuestionnaire新增问卷测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>insertQuestionnaire新增问卷测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 更新问卷
    public void updateQuestionnaireTest() throws Exception {
        Questionnaire questionnaire = new Questionnaire("1", "1779cd18-cc34-47b2-a453-419fcff8df3b", "3", "4", new Date(), new Date(), new Date(), "5", 0, 0, 0);
        String jsonProject = new ObjectMapper().writeValueAsString(questionnaire);
        mockMvc.perform(MockMvcRequestBuilders.post("/updateQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>updateQuestionnaire更新问卷测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>updateQuestionnaire更新问卷测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 删除问卷
    public void deleteQuestionnaireTest() throws Exception {
        String id = "4173bc9e-1a42-4747-97e2-775f3ac88714";
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>deleteQuestionnaire删除问卷测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>deleteQuestionnaire删除问卷测试失败");
                    }
                });
    }
}
