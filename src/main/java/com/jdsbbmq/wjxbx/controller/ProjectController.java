package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.bean.project.QueryRequest;
import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import com.jdsbbmq.wjxbx.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Project> selectAllProject(@RequestBody String userId) {
        List<ProjectEntity> projectEntityList = projectService.selectAllProject(userId);
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(new Project(projectEntity));
        }
        return projectList;
    }

    // 根据id查询项目
    @Operation(summary = "根据id查询项目", description = "能为用户查询他的某个项目")
    @RequestMapping(value = "/selectProjectById", method = RequestMethod.POST)
    public Project selectProjectById(@RequestBody String id) {
        ProjectEntity projectEntity = projectService.selectProjectById(id);
        return new Project(projectEntity);
    }

    //根据projectName查询项目
    @Operation(summary = "根据名称查询项目", description = "能为用户查询他的项目")
    @RequestMapping(value = "/selectProjectByName", method = RequestMethod.POST)
    public List<Project> selectProjectByName(@RequestBody String projectName) {
        List<ProjectEntity> projectEntityList = projectService.selectProjectByName(projectName);
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(new Project(projectEntity));
        }
        return projectList;
    }

    //分页寻找项目
    @Operation(summary = "分页寻找项目", description = "能为用户分页寻找他的项目")
    @RequestMapping(value = "/selectProjectByPage", method = RequestMethod.POST)
    public List<Project> selectProjectByPage(@RequestBody QueryRequest queryRequest) {
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
        List<ProjectEntity> projectEntityList = projectService.selectProjectByPage(queryRequest);
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(new Project(projectEntity));
        }
        return projectList;
    }

    /*
        增删改
     */

    // 插入项目
    @Operation(summary = "插入项目", description = "向数据库中插入该项目")
    @RequestMapping(value = "/insertProject", method = RequestMethod.POST)
    public int insertProject(@RequestBody Project project) throws ParseException {
        project.init();
        ProjectEntity projectEntity = new ProjectEntity(project);
        return projectService.insertProject(projectEntity);
    }

    // 更新项目
    @Operation(summary = "更新项目", description = "向数据库中更新该项目")
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public int updateProject(@RequestBody Project project) throws ParseException {
        ProjectEntity projectEntity = new ProjectEntity(project);
        return projectService.updateProject(projectEntity);
    }

    //更新项目的星标状态
    @Operation(summary = "将项目变为收藏项目", description = "往星标项目表中加入项目")
    @RequestMapping(value = "/updateStarOnProject", method = RequestMethod.POST)
    public int updateStarOnProject(@RequestBody String id) {
        return projectService.updateStarOnProject(id);
    }

    //更新项目的星标状态
    @Operation(summary = "将项目取消收藏项目", description = "往项目从星标项目中移除")
    @RequestMapping(value = "/updateStarOffProject", method = RequestMethod.POST)
    public int updateStarOffProject(@RequestBody String id) {
        return projectService.updateStarOffProject(id);
    }

    //更新项目的删除状态
    @Operation(summary = "将项目变为删除项目", description = "往删除项目表中加入项目")
    @RequestMapping(value = "/updateDeleteOnProject", method = RequestMethod.POST)
    public int updateDeleteOnProject(@RequestBody String id) {
        return projectService.updateDeleteOnProject(id);
    }

    //更新项目的删除状态
    @Operation(summary = "将项目取消删除项目", description = "往项目从删除项目中移除")
    @RequestMapping(value = "/updateDeleteOffProject", method = RequestMethod.POST)
    public int updateDeleteOffProject(@RequestBody String id) {
        return projectService.updateDeleteOffProject(id);
    }

    // 删除项目
    @Operation(summary = "删除项目", description = "从数据库中删除该项目")
    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    public int deleteProject(@RequestBody String id) {
        return projectService.deleteProjectById(id);
    }

    //清空回收站
    @Operation(summary = "清空回收站", description = "从数据库中删除该用户的所有删除项目")
    @RequestMapping(value = "/deleteAllProjectRecycled", method = RequestMethod.POST)
    public int deleteAllProjectRecycled(@RequestBody String userId) {
        return projectService.deleteAllProjectRecycled(userId);
    }
}
