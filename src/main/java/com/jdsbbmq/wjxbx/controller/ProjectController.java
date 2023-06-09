package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class ProjectController {
    @Resource
    private ProjectService projectService;

    /*
        查询
     */
    //查询所有项目
    @Operation(summary = "用户所有项目接口", description = "能为用户列出他的所有项目")
    @RequestMapping(value = "/selectAllProject", method = RequestMethod.POST)
    public CompletableFuture<List<Project>> selectAllProject(@RequestBody String userId) throws ParseException {
        return projectService.selectAllProject(userId);
    }

    // 根据id查询项目
    @Operation(summary = "根据id查询项目", description = "能为用户查询他的某个项目")
    @RequestMapping(value = "/selectProjectById", method = RequestMethod.POST)
    public CompletableFuture<Project> selectProjectById(@RequestBody String id) throws ParseException {
        return projectService.selectProjectById(id);
    }

    //根据projectName查询项目
    @Operation(summary = "根据名称查询项目", description = "能为用户查询他的项目")
    @RequestMapping(value = "/selectProjectByName", method = RequestMethod.POST)
    public CompletableFuture<List<Project>> selectProjectByName(@RequestBody String projectName) throws ParseException {

        return projectService.selectProjectByName(projectName);
    }

    //分页寻找项目
    @Operation(summary = "分页寻找项目", description = "能为用户分页寻找他的项目")
    @RequestMapping(value = "/selectProjectByPage", method = RequestMethod.POST)
    public CompletableFuture<List<Project>> selectProjectByPage(@RequestBody QueryRequest queryRequest) throws ParseException {
        return projectService.selectProjectByPage(queryRequest);
    }

    /*
        增删改
     */

    // 插入项目
    @Operation(summary = "插入项目", description = "向数据库中插入该项目")
    @RequestMapping(value = "/insertProject", method = RequestMethod.POST)
    public CompletableFuture<Integer> insertProject(@RequestBody Project project) throws ParseException {
        return projectService.insertProject(project);
    }

    // 复制项目
    @Operation(summary = "复制项目", description = "向数据库中插入该项目")
    @RequestMapping(value = "/insertCopyProject", method = RequestMethod.POST)
    public CompletableFuture<Integer> insertCopyProject(@RequestBody String id) throws ParseException {
        return projectService.insertCopyQuestionnaire(id);
    }

    // 更新项目
    @Operation(summary = "更新项目", description = "向数据库中更新该项目")
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateProject(@RequestBody Project project) throws ParseException {
        return projectService.updateProject(project);
    }

    //更新项目的星标状态
    @Operation(summary = "将项目变为收藏项目", description = "往星标项目表中加入项目")
    @RequestMapping(value = "/updateStarOnProject", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateStarOnProject(@RequestBody String id) {
        return projectService.updateStarOnProject(id);
    }

    //更新项目的星标状态
    @Operation(summary = "将项目取消收藏项目", description = "往项目从星标项目中移除")
    @RequestMapping(value = "/updateStarOffProject", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateStarOffProject(@RequestBody String id) {
        return projectService.updateStarOffProject(id);
    }

    //更新项目的删除状态
    @Operation(summary = "将项目变为删除项目", description = "往删除项目表中加入项目")
    @RequestMapping(value = "/updateDeletedOnProject", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateDeletedOnProject(@RequestBody String id) {
        return projectService.updateDeletedOnProject(id);
    }

    //更新项目的删除状态
    @Operation(summary = "将项目取消删除项目", description = "往项目从删除项目中移除")
    @RequestMapping(value = "/updateDeletedOffProject", method = RequestMethod.POST)
    public CompletableFuture<Integer> updateDeletedOffProject(@RequestBody String id) {
        return projectService.updateDeletedOffProject(id);
    }

    // 删除项目
    @Operation(summary = "删除项目", description = "从数据库中删除该项目")
    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    public CompletableFuture<Integer> deleteProject(@RequestBody String id) {
        return projectService.deleteProjectById(id);
    }

    // 删除项目通过ID
    @Operation(summary = "删除项目通过ID", description = "从数据库中删除该项目")
    @RequestMapping(value = "/deleteProjectById", method = RequestMethod.POST)
    public CompletableFuture<Integer> deleteProjectById(@RequestBody String id) {
        return projectService.deleteProjectById(id);
    }

    //清空回收站
    @Operation(summary = "清空回收站", description = "从数据库中删除该用户的所有删除项目")
    @RequestMapping(value = "/deleteAllProjectRecycled", method = RequestMethod.POST)
    public CompletableFuture<Integer> deleteAllProjectRecycled(@RequestBody String userId) {
        return projectService.deleteAllProjectRecycled(userId);
    }
}
