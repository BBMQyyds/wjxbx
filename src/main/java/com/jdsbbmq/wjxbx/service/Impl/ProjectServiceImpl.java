package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.dao.ProjectEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import com.jdsbbmq.wjxbx.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectEntityMapper projectEntityMapper;


      /*
        查询
     */

    //查询该用户下的所有项目
    @Override
    public List<Project> selectAllProject(String userId) {
        List<ProjectEntity> projectEntityList = projectEntityMapper.selectAllProject(userId);
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(new Project(projectEntity));
        }
        return projectList;
    }

    //根据id查询项目
    @Override
    public Project selectProjectById(String id) {
        return new Project(projectEntityMapper.selectProjectById(id));
    }

    //根据projectName查询项目
    @Override
    public List<Project> selectProjectByName(String projectName) {
        List<ProjectEntity> projectEntityList = projectEntityMapper.selectProjectByName(projectName);
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(new Project(projectEntity));
        }
        return projectList;
    }

    @Override
    public List<Project> selectProjectByPage(QueryRequest queryRequest) {
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
        List<ProjectEntity> projectEntityList = projectEntityMapper.selectProjectByPage(queryRequest.ToQueryEntity());
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
    @Override
    public int insertProject(Project project) {
        try {
            project.init();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ProjectEntity projectEntity = new ProjectEntity(project);
        return projectEntityMapper.insertProject(projectEntity);
    }

    //更新项目
    @Override
    public int updateProject(Project project) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lastUpdateDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的最后更新时间
        ProjectEntity projectEntity = new ProjectEntity(project);
        projectEntity.setLastUpdateDate(lastUpdateDate);
        return projectEntityMapper.updateProject(projectEntity);
    }

    //将项目的收藏更新为已收藏
    @Override
    public int updateStarOnProject(String id) {
        return projectEntityMapper.updateStarOnProject(id);
    }

    @Override
    public int updateStarOffProject(String id) {
        return projectEntityMapper.updateStarOffProject(id);
    }

    @Override
    public int updateDeletedOnProject(String id) {
        return projectEntityMapper.updateDeletedOnProject(id);
    }

    @Override
    public int updateDeletedOffProject(String id) {
        return projectEntityMapper.updateDeletedOffProject(id);
    }

    //删除项目
    @Override
    public int deleteProjectById(String id) {
        return projectEntityMapper.deleteProjectById(id);
    }

    //清空回收站
    @Override
    public int deleteAllProjectRecycled(String userId) {
        return projectEntityMapper.deleteAllProjectRecycled(userId);
    }
}
