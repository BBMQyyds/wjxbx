package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.project.Project;
import jakarta.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ProjectControllerTest {
    Logger log = Logger.getLogger(UserControllerTests.class);
    @Resource
    private ProjectController projectController;
    @Test
    void contextLoads() {

    }

    @Test
    // 查询所有项目
    public void selectAllProjectTest(){
        String userId="\"53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f\"";
        List<Project> projectList= projectController.selectAllProject(userId);
        if (projectList==null) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("ProjectController: >>selectAllProject所有项目查询测试失败");
        } else {
            System.out.println(projectList);
            // 记录info级别的信息
            log.info("ProjectController: >>selectAllProject所有项目查询测试成功");
        }
    }

    @Test
    // 根据id查询项目
    public void selectProjectByIdTest(){
        String id="\"0f796ce7-28f5-405e-b7f5-682e6cbca8e2\"";
        Project project=projectController.selectProjectById(id);
        if (project == null) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("ProjectController: >>selectProjectById根据id查询项目测试失败");
        } else {
            System.out.println(project);
            // 记录info级别的信息
            log.info("ProjectController: >>selectProjectById根据id查询项目测试成功");
        }
    }

    @Test
    // 根据名称查询项目
    public void selectProjectByNameTest(){
        String projectName="\"风筝审批\"";
        List<Project> projectList=projectController.selectProjectByName(projectName);
        if (CollectionUtils.isEmpty(projectList)) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("ProjectController: >>selectProjectByName根据名称查询项目测试失败");
        } else {
            System.out.println(projectList);
            // 记录info级别的信息
            log.info("ProjectController: >>selectProjectByName根据名称查询项目测试成功");
        }
    }

    @Test
    @Transactional
    // 插入项目
    public void insertProjectTest() throws ParseException {
        Project project=new Project("1","2","3","4","5",new Date(),"6",new Date());
        int i=projectController.insertProject(project);
        // 记录info级别的信息
        if(i==1){
            log.info("ProjectController: >>insertProject插入项目测试成功");
        }else{
            log.error("ProjectController: >>insertProject插入项目测试失败");
        }
    }

    @Test
    @Transactional
    // 更新项目
    public void updateProjectTest() throws ParseException {
        Project project=new Project("1","2","3","4","5",new Date(),"6",new Date());
        int i = projectController.updateProject(project);
        // 记录info级别的信息
        if (i == 1) {
            log.info("ProjectController: >>updateProject更新项目测试成功");
        } else {
            log.error("ProjectController: >>updateProject更新项目测试失败");
        }
    }

    @Test
    @Transactional
    // 删除项目
    public void deleteProjectTest(){
        String id="1";
        int i=projectController.deleteProject(id);
        // 记录info级别的信息
        if(i==1){
            log.info("ProjectController: >>deleteProject删除项目测试成功");
        }else{
            log.error("ProjectController: >>deleteProject删除项目测试失败");
        }
    }
}
