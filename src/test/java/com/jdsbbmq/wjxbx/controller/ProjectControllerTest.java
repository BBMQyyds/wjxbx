package com.jdsbbmq.wjxbx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdsbbmq.wjxbx.bean.project.Project;
import jakarta.annotation.Resource;
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
public class ProjectControllerTest {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }
    @Test
    // 查询所有项目
    public void selectAllProjectTest() throws Exception {
        String userId = "\"53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f\"";
        String jsonProject = new ObjectMapper().writeValueAsString(userId);
        mockMvc.perform(MockMvcRequestBuilders.post("/selectAllProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
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

    @Test
    // 根据id查询项目
    public void selectProjectByIdTest() throws Exception {
        String id = "0f796ce7-28f5-405e-b7f5-682e6cbca8e2";
        String jsonProject = new ObjectMapper().writeValueAsString(id);
        mockMvc.perform(MockMvcRequestBuilders.post("/selectProjectById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        System.out.println(result.getResponse().getContentAsString());
                        // 记录info级别的信息
                        log.info("ProjectController: >>selectProjectById根据id查询项目测试成功");
                    } else {
                        System.out.println("查询失败");
                        // 记录error级别的信息
                        log.error("ProjectController: >>selectProjectById根据id查询项目测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 根据名称查询项目
    public void selectProjectByNameTest() throws Exception {
        String projectName = "风筝审批";
        String jsonProject = new ObjectMapper().writeValueAsString(projectName);
        mockMvc.perform(MockMvcRequestBuilders.post("/selectProjectByName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        System.out.println(result.getResponse().getContentAsString());
                        // 记录info级别的信息
                        log.info("ProjectController: >>selectProjectByName根据名称查询项目测试成功");
                    } else {
                        System.out.println("查询失败");
                        // 记录error级别的信息
                        log.error("ProjectController: >>selectProjectByName根据名称查询项目测试失败");
                    }
                });
    }

    @Test
    @Transactional
    public void insertProjectTest() throws Exception {
        Project project = new Project("1", "2", "3", "4", "5", new Date(), "6", new Date());
        String jsonProject = new ObjectMapper().writeValueAsString(project);

        mockMvc.perform(MockMvcRequestBuilders.post("/insertProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    if (result.getResponse().getStatus() == 200) {
                        log.info("ProjectController: >>insertProject插入项目测试成功");
                    } else {
                        log.error("ProjectController: >>insertProject插入项目测试失败");
                    }
                });
    }


    @Test
    @Transactional
    // 更新项目
    public void updateProjectTest() throws Exception {
        Project project = new Project("1", "2", "3", "4", "5", new Date(), "6", new Date());
        String jsonProject = new ObjectMapper().writeValueAsString(project);
        mockMvc.perform(MockMvcRequestBuilders.post("/updateProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        log.info("ProjectController: >>updateProject更新项目测试成功");
                    } else {
                        log.error("ProjectController: >>updateProject更新项目测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 删除项目
    public void deleteProjectTest() throws Exception {
        String id = "1";
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        log.info("ProjectController: >>updateProject更新项目测试成功");
                    } else {
                        log.error("ProjectController: >>updateProject更新项目测试失败");
                    }
                });
    }
}
