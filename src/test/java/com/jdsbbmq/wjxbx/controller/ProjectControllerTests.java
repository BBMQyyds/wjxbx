package com.jdsbbmq.wjxbx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.project.Project;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@AutoConfigureMockMvc
@SpringBootTest
public class ProjectControllerTests {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    // 查询所有项目
    public void selectAllProjectTest() throws Exception {
        String userId = "53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f";
        mockMvc.perform(MockMvcRequestBuilders.post("/selectAllProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userId))
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
        mockMvc.perform(MockMvcRequestBuilders.post("/selectProjectById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
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
    // 根据名称查询项目
    public void selectProjectByNameTest() throws Exception {
        String projectName = "风筝审批";
        mockMvc.perform(MockMvcRequestBuilders.post("/selectProjectByName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectName))
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
    // 分页查找项目
    public void selectProjectByPageTest() throws Exception {
        QueryRequest queryRequest = new QueryRequest("862c186d-b73b-49d7-af4c-b3b6effe799e", "1", "2", "3", 1, 2, 3, "4");
        String jsonProject = new ObjectMapper().writeValueAsString(queryRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/selectProjectByPage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        System.out.println(result.getResponse().getContentAsString());
                        // 记录info级别的信息
                        log.info("ProjectController: >>selectProjectByPage分页查找项目测试成功");
                    } else {
                        System.out.println("查询失败");
                        // 记录error级别的信息
                        log.error("ProjectController: >>selectProjectByPage分页查找项目测试失败");
                    }
                });
    }

    @Test
    @Transactional
    @Rollback
    public void insertProjectTest() throws Exception {
        Project project = new Project("1", "2", "3", "4", "5", new Date(), "6", new Date(), 0, 0, 0);
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
    //复制项目
    public void insertCopyProjectTest() throws Exception {
        String id = "0f796ce7-28f5-405e-b7f5-682e6cbca8e2";
        mockMvc.perform(MockMvcRequestBuilders.post("/insertCopyProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    if (result.getResponse().getStatus() == 200) {
                        log.info("ProjectController: >>insertCopyProject复制项目测试成功");
                    } else {
                        log.error("ProjectController: >>insertCopyProject复制项目测试失败");
                    }
                });
    }


    @Test
    @Transactional
    // 更新项目
    public void updateProjectTest() throws Exception {
        Project project = new Project("1", "2", "3", "4", "5", new Date(), "6", new Date(), 0, 0, 0);
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
    // 更新项目收藏状态
    public void updateProjectCollectTest() throws Exception {
        String id = "0f796ce7-28f5-405e-b7f5-682e6cbca8e2";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateStarOnProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        log.info("ProjectController: >>updateStarOnProject更新项目测试成功");
                    } else {
                        log.error("ProjectController: >>updateStarOnProject更新项目测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 取消项目收藏状态
    public void updateProjectCollectCancelTest() throws Exception {
        String id = "0f796ce7-28f5-405e-b7f5-682e6cbca8e2";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateStarOffProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        log.info("ProjectController: >>updateStarOffProject更新项目测试成功");
                    } else {
                        log.error("ProjectController: >>updateStarOffProject更新项目测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 更新项目删除状态
    public void updateDeletedOnProjectTest() throws Exception {
        String id = "0f796ce7-28f5-405e-b7f5-682e6cbca8e2";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateDeletedOnProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        log.info("ProjectController: >>updateDeletedOnProject更新项目测试成功");
                    } else {
                        log.error("ProjectController: >>updateDeletedOnProject更新项目测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 取消项目删除状态
    public void updateDeletedOffProjectTest() throws Exception {
        String id = "0f796ce7-28f5-405e-b7f5-682e6cbca8e2";
        mockMvc.perform(MockMvcRequestBuilders.post("/updateDeletedOffProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        log.info("ProjectController: >>updateDeletedOffProject更新项目测试成功");
                    } else {
                        log.error("ProjectController: >>updateDeletedOffProject更新项目测试失败");
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

    @Test
    @Transactional
    //清空回收站
    public void deleteAllProjectRecycledTest() throws Exception {
        String id = "53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f";
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteAllProjectRecycled")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        log.info("ProjectController: >>deleteAllProject清空回收站测试成功");
                    } else {
                        log.error("ProjectController: >>deleteAllProject清空回收站测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //根据Id删除项目
    public void deleteProjectByIdTest() throws Exception {
        String id = "53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f";
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteProjectById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        log.info("ProjectController: >>deleteProjectById根据Id删除项目测试成功");
                    } else {
                        log.error("ProjectController: >>deleteProjectById根据Id删除项目测试失败");
                    }
                });
    }
}
