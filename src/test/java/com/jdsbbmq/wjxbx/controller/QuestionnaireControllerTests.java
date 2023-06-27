package com.jdsbbmq.wjxbx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdsbbmq.wjxbx.bean.QueryRequest;
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
    // 分页查询问卷
    public void selectQuestionnaireByPageTest() throws Exception {
        QueryRequest queryRequest = new QueryRequest("04a0dba4-06fe-49dc-8200-3a2c92021803", "1", "2", "3", 1, 2, 3, "4");
        String jsonProject = new ObjectMapper().writeValueAsString(queryRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/selectQuestionnaireByPage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>selectQuestionnaireByPage分页查询问卷测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>selectQuestionnaireByPage分页查询问卷测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 新增问卷
    public void insertQuestionnaireTest() throws Exception {
        Questionnaire questionnaire = new Questionnaire("2", "1779cd18-cc34-47b2-a453-419fcff8df3b", "3", "4", new Date(), new Date(), new Date(), 0, 0, 0, 0);
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
        Questionnaire questionnaire = new Questionnaire("1", "1779cd18-cc34-47b2-a453-419fcff8df3b", "3", "4", new Date(), new Date(), new Date(), 0, 0, 0, 0);
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
    // 更新问卷收藏状态
    public void updateStarOnQuestionnaireTest() throws Exception {
        String id = "04a0dba4-06fe-49dc-8200-3a2c92021803";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateStarOnQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>updateStarOnQuestionnaire更新问卷收藏状态测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>updateStarOnQuestionnaire更新问卷收藏状态测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 更新取消问卷状态
    public void updateStarOffQuestionnaireTest() throws Exception {
        String id = "04a0dba4-06fe-49dc-8200-3a2c92021803";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateStarOffQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>updateCancelOnQuestionnaire更新取消问卷状态测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>updateCancelOnQuestionnaire更新取消问卷状态测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //更新问卷取消状态
    public void updateDeletedOffQuestionnaireTest() throws Exception {
        String id = "04a0dba4-06fe-49dc-8200-3a2c92021803";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateDeletedOffQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>updateDeletedOffQuestionnaire更新问卷取消状态测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>updateDeletedOffQuestionnaire更新问卷取消状态测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //更新问卷删除状态
    public void updateDeletedOnQuestionnaireTest() throws Exception {
        String id = "04a0dba4-06fe-49dc-8200-3a2c92021803";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateDeletedOnQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>updateDeletedOnQuestionnaire更新问卷删除状态测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>updateDeletedOnQuestionnaire更新问卷删除状态测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //更新一个问卷的发布状态
    public void updateReleaseQuestionnaireTest() throws Exception {
        String id = "04a0dba4-06fe-49dc-8200-3a2c92021803";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateReleaseQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>updateReleaseQuestionnaire更新一个问卷的发布状态测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>updateReleaseQuestionnaire更新一个问卷的发布状态测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //更新一个问卷的取消发布状态
    public void updateReclaimQuestionnaireTest() throws Exception {
        String id = "04a0dba4-06fe-49dc-8200-3a2c92021803";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateReclaimQuestionnaire")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("QuestionnaireController: >>updateReclaimQuestionnaire更新一个问卷的取消发布状态测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("QuestionnaireController: >>updateReclaimQuestionnaire更新一个问卷的取消发布状态测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 删除问卷
    public void deleteQuestionnaireTest() throws Exception {
        String id = "212b800d-c023-484d-912d-60d8382071ce";
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
