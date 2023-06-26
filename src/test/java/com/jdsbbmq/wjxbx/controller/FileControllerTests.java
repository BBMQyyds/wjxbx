package com.jdsbbmq.wjxbx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdsbbmq.wjxbx.bean.file.File;
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
public class FileControllerTests {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    //插入一个文件夹
    public void insertFileTest() throws Exception {
        File file=new File("216c6b4f-12e2-4012-a029-37b15c965599","1",new Date(),"2",new Date(),"216c6b4f-12e2-4012-a029-37b15c965547");
        String jsonProject = new ObjectMapper().writeValueAsString(file);
        mockMvc.perform(MockMvcRequestBuilders.post("/insertFile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("FileController: >>insertFile插入文件夹测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("FileController: >>insertFile插入文件夹测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //复制一个文件夹
    public void insertCopyFileTest() throws Exception {
        File file=new File("216c6b4f-12e2-4012-a029-37b15c965599","1",new Date(),"2",new Date(),"216c6b4f-12e2-4012-a029-37b15c965547");
        String jsonProject = new ObjectMapper().writeValueAsString(file);
        mockMvc.perform(MockMvcRequestBuilders.post("/insertCopyFile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("FileController: >>insertCopyFile复制文件夹测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("FileController: >>insertCopyFile复制文件夹测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //更新一个文件夹
    public void updateFileTest() throws Exception {
        File file=new File("216c6b4f-12e2-4012-a029-37b15c965599","1",new Date(),"2",new Date(),"216c6b4f-12e2-4012-a029-37b15c965547");
        String jsonProject = new ObjectMapper().writeValueAsString(file);
        mockMvc.perform(MockMvcRequestBuilders.post("/updateFile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("FileController: >>updateFile更新文件夹测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("FileController: >>updateFile更新文件夹测试失败");
                    }
                });
    }

    @Test
    @Transactional
    //删除一个文件夹
    public void deleteFileTest() throws Exception {
        File file=new File("216c6b4f-12e2-4012-a029-37b15c965599","1",new Date(),"2",new Date(),"216c6b4f-12e2-4012-a029-37b15c965547");
        String jsonProject = new ObjectMapper().writeValueAsString(file);
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteFile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProject))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    int status = result.getResponse().getStatus();
                    if (status == 200) {
                        // 记录info级别的信息
                        log.info("FileController: >>deleteFile删除文件夹测试成功");
                    } else {
                        // 记录error级别的信息
                        log.error("FileController: >>deleteFile删除文件夹测试失败");
                    }
                });
    }
}
