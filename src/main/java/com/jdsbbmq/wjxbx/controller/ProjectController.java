package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import com.jdsbbmq.wjxbx.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {
    @Resource
    private ProjectService projectService;
    //查询所有项目
    @Operation(summary = "用户所有项目接口", description = "能为用户列出他的所有项目")
    @RequestMapping(value = "/selectAllProject", method = RequestMethod.POST)
    public List<Project> selectAllProject(@RequestBody String userId) {
        List<ProjectEntity> projectEntityList = projectService.selectAllProject(userId);
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(projectEntity.toProject());
        }
        return projectList;
    }

    // 根据id查询项目
    @Operation(summary = "根据id查询项目", description = "能为用户查询他的某个项目")
    @RequestMapping(value = "/selectProjectById", method = RequestMethod.POST)
    public Project selectProjectById(@RequestBody String id) {
        ProjectEntity projectEntity = projectService.selectProjectById(id);
        return projectEntity.toProject();
    }

    /*
        增删改
     */

    // 插入项目
    @Operation(summary = "插入项目", description = "向数据库中插入该项目")
    @RequestMapping(value = "/insertProject", method = RequestMethod.POST)
    public int insertProject(@RequestBody Project project) {
        project.init();
        ProjectEntity projectEntity = project.toProjectEntity();
        return projectService.insertProject(projectEntity);
    }

    // 更新项目
    @Operation(summary = "更新项目", description = "向数据库中更新该项目")
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public int updateProject(@RequestBody Project project) {
        ProjectEntity projectEntity = project.toProjectEntity();
        return projectService.updateProject(projectEntity);
    }

    // 删除项目
    @Operation(summary = "删除项目", description = "从数据库中删除该项目")
    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    public int deleteProject(@RequestBody String id) {
        return projectService.deleteProjectById(id);
    }
}
