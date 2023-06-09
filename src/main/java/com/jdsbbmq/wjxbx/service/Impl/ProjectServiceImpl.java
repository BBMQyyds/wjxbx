package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.dao.ProjectEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import com.jdsbbmq.wjxbx.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
    public List<ProjectEntity> selectAllProject(String userId) {
        return projectEntityMapper.selectAllProject(userId);
    }

    // 根据id查询项目
    @Override
    public ProjectEntity selectProjectById(String id) {
        return projectEntityMapper.selectProjectById(id);
    }


    /*
        增删改
     */

    // 插入项目
    @Override
    public int insertProject(ProjectEntity projectEntity) {
        return projectEntityMapper.insertProject(projectEntity);
    }

    @Override
    public int updateProject(ProjectEntity projectEntity) {
        return projectEntityMapper.updateProject(projectEntity);
    }

    @Override
    public int deleteProjectById(String id) {
        return projectEntityMapper.deleteProjectById(id);
    }
}
