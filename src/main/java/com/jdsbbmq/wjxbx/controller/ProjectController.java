package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @Resource
    @Qualifier("asyncServiceExecutor")
    Executor executor;

    /*
        查询
     */
    //查询所有项目
    @Operation(summary = "用户所有项目接口", description = "能为用户列出他的所有项目")
    @RequestMapping(value = "/selectAllProject", method = RequestMethod.POST)
    public List<Project> selectAllProject(@RequestBody String userId) {
        CompletableFuture<List<Project>> projects = CompletableFuture.supplyAsync(() -> {
            try {
                return projectService.selectAllProject(userId);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return projects.join();
    }

    // 根据id查询项目
    @Operation(summary = "根据id查询项目", description = "能为用户查询他的某个项目")
    @RequestMapping(value = "/selectProjectById", method = RequestMethod.POST)
    public Project selectProjectById(@RequestBody String id) {
        CompletableFuture<Project> project = CompletableFuture.supplyAsync(() -> {
            try {
                return projectService.selectProjectById(id);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return project.join();
    }

    //根据projectName查询项目
    @Operation(summary = "根据名称查询项目", description = "能为用户查询他的项目")
    @RequestMapping(value = "/selectProjectByName", method = RequestMethod.POST)
    public List<Project> selectProjectByName(@RequestBody String projectName) {
        CompletableFuture<List<Project>> projects = CompletableFuture.supplyAsync(() -> {
            try {
                return projectService.selectProjectByName(projectName);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return projects.join();
    }

    //分页寻找项目
    @Operation(summary = "分页寻找项目", description = "能为用户分页寻找他的项目")
    @RequestMapping(value = "/selectProjectByPage", method = RequestMethod.POST)
    public List<Project> selectProjectByPage(@RequestBody QueryRequest queryRequest) {
        CompletableFuture<List<Project>> projects = CompletableFuture.supplyAsync(() -> {
            try {
                return projectService.selectProjectByPage(queryRequest);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return projects.join();
    }

    /*
        增删改
     */

    // 插入项目
    @Operation(summary = "插入项目", description = "向数据库中插入该项目")
    @RequestMapping(value = "/insertProject", method = RequestMethod.POST)
    public Integer insertProject(@RequestBody Project project) {
        CompletableFuture<Integer> insertProject = CompletableFuture.supplyAsync(() ->
                projectService.insertProject(project), executor);
        return insertProject.join();
    }

    // 复制项目
    @Operation(summary = "复制项目", description = "向数据库中插入该项目")
    @RequestMapping(value = "/insertCopyProject", method = RequestMethod.POST)
    public Integer insertCopyProject(@RequestBody String id) {
        CompletableFuture<Integer> insertCopyProject = CompletableFuture.supplyAsync(() -> {
            try {
                return projectService.insertCopyQuestionnaire(id);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return insertCopyProject.join();
    }

    // 更新项目
    @Operation(summary = "更新项目", description = "向数据库中更新该项目")
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public Integer updateProject(@RequestBody Project project) {
        CompletableFuture<Integer> updateProject = CompletableFuture.supplyAsync(() -> {
            try {
                return projectService.updateProject(project);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return updateProject.join();
    }

    //更新项目的星标状态
    @Operation(summary = "将项目变为收藏项目", description = "往星标项目表中加入项目")
    @RequestMapping(value = "/updateStarOnProject", method = RequestMethod.POST)
    public Integer updateStarOnProject(@RequestBody String id) {
        CompletableFuture<Integer> updateStarOnProject = CompletableFuture.supplyAsync(() ->
                projectService.updateStarOnProject(id), executor);
        return updateStarOnProject.join();
    }

    //更新项目的星标状态
    @Operation(summary = "将项目取消收藏项目", description = "往项目从星标项目中移除")
    @RequestMapping(value = "/updateStarOffProject", method = RequestMethod.POST)
    public Integer updateStarOffProject(@RequestBody String id) {
        CompletableFuture<Integer> updateStarOffProject = CompletableFuture.supplyAsync(() ->
                projectService.updateStarOffProject(id), executor);
        return updateStarOffProject.join();
    }

    //更新项目的删除状态
    @Operation(summary = "将项目变为删除项目", description = "往删除项目表中加入项目")
    @RequestMapping(value = "/updateDeletedOnProject", method = RequestMethod.POST)
    public Integer updateDeletedOnProject(@RequestBody String id) {
        CompletableFuture<Integer> updateDeletedOnProject = CompletableFuture.supplyAsync(() ->
                projectService.updateDeletedOnProject(id), executor);
        return updateDeletedOnProject.join();
    }

    //更新项目的删除状态
    @Operation(summary = "将项目取消删除项目", description = "往项目从删除项目中移除")
    @RequestMapping(value = "/updateDeletedOffProject", method = RequestMethod.POST)
    public Integer updateDeletedOffProject(@RequestBody String id) {
        CompletableFuture<Integer> updateDeletedOffProject = CompletableFuture.supplyAsync(() ->
                projectService.updateDeletedOffProject(id), executor);
        return updateDeletedOffProject.join();
    }

    // 删除项目
    @Operation(summary = "删除项目", description = "从数据库中删除该项目")
    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    public Integer deleteProject(@RequestBody String id) {
        CompletableFuture<Integer> deleteProject = CompletableFuture.supplyAsync(() ->
                projectService.deleteProjectById(id), executor);
        return deleteProject.join();
    }

    // 删除项目通过ID
    @Operation(summary = "删除项目通过ID", description = "从数据库中删除该项目")
    @RequestMapping(value = "/deleteProjectById", method = RequestMethod.POST)
    public Integer deleteProjectById(@RequestBody String id) {
        CompletableFuture<Integer> deleteProjectById = CompletableFuture.supplyAsync(() ->
                projectService.deleteProjectById(id), executor);
        return deleteProjectById.join();
    }

    //清空回收站
    @Operation(summary = "清空回收站", description = "从数据库中删除该用户的所有删除项目")
    @RequestMapping(value = "/deleteAllProjectRecycled", method = RequestMethod.POST)
    public Integer deleteAllProjectRecycled(@RequestBody String userId) {
        CompletableFuture<Integer> deleteAllProjectRecycled = CompletableFuture.supplyAsync(() ->
                projectService.deleteAllProjectRecycled(userId), executor);
        return deleteAllProjectRecycled.join();
    }
}
