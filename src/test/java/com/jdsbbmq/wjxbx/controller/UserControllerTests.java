package com.jdsbbmq.wjxbx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.bean.user.LoginRequest;
import com.jdsbbmq.wjxbx.bean.user.User;
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

import java.util.UUID;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTests {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {

    }

    @Test
    // 登录
    public void loginTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("卜部美琴");
        loginRequest.setPassword("e10adc3949ba59abbe56e057f20f883e");
        String jsonProject = new ObjectMapper().writeValueAsString(loginRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("UserController: >>login登录测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("UserController: >>login登录测试失败");
                    }
                });
    }

    @Test
    // 查询所有用户
    public void selectAllTest() throws Exception {
        //调用userMapper的方法
        mockMvc.perform(MockMvcRequestBuilders.post("/selectAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("UserController: >>selectAll选取全部方法测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("UserController: >>selectAll选取全部方法测试失败");
                    }
                });
    }

    @Test
    // 根据id查询用户
    public void selectUserByIdTest() throws Exception {
        String id = "53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f";
        //调用userMapper的方法
        mockMvc.perform(MockMvcRequestBuilders.post("/selectUserById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("UserController: >>selectUserById根据id选取用户方法测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("");
                    }
                });
    }

    @Test
    @Transactional
    // 插入用户
    public void insertUserTest() throws Exception {
        //调用userMapper的方法
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setStatus(1);
        user.setUsername("LS");
        user.setPassword("123");
        String jsonProject = new ObjectMapper().writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/insertUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("UserController: >>insertUser用户插入测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("UserController: >>insertUser用户插入测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 更新用户
    public void updateUserTest() throws Exception {
        //调用userMapper的方法
        User user = new User();
        user.setId("216c6b4f-12e2-4012-a029-37b15c965547");
        user.setStatus(1);
        user.setUsername("LS");
        user.setPassword("123");
        String jsonProject = new ObjectMapper().writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("UserController: >>updateUser用户更新测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("UserController: >>updateUser用户更新测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //更新密码
    public void updatePasswordTest() throws Exception {
        ChangeRequest changeRequest = new ChangeRequest("风筝追风", "e10adc3949ba59abbe56e057f20f883e",
                "e10adc3949ba59abbe56e057f20f883e", "风筝追风");
        //调用userMapper的方法
        String jsonProject = new ObjectMapper().writeValueAsString(changeRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/updatePassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("UserController: >>updatePassword密码更新测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("UserController: >>updatePassword密码更新测试失败");
                    }
                });
    }

    @Test
    @Transactional
    // 根据id删除用户
    public void deleteUserByIdTest() throws Exception {
        String id = "4f48ba43-e933-4cef-ac7d-094e1e70c555";
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteUserById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("UserController: >>deleteUserById用户删除测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("UserController: >>deleteUserById用户删除测试失败");
                    }
                });
    }

}
