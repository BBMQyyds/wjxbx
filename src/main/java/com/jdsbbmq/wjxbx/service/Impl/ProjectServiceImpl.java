package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.project.QueryRequest;
import com.jdsbbmq.wjxbx.dao.ProjectEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import com.jdsbbmq.wjxbx.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<ProjectEntity> selectAllProject(String userId) {
        return projectEntityMapper.selectAllProject(userId);
    }

    //根据id查询项目
    @Override
    public ProjectEntity selectProjectById(String id) {
        return projectEntityMapper.selectProjectById(id);
    }

    //根据projectName查询项目
    @Override
    public List<ProjectEntity> selectProjectByName(String projectName) {
        return projectEntityMapper.selectProjectByName(projectName);
    }

    @Override
    public List<ProjectEntity> selectProjectByPage(QueryRequest queryRequest) {
        QueryEntity queryEntity=queryRequest.ToQueryEntity();
        return projectEntityMapper.selectProjectByPage(queryEntity);
    }


    /*
        增删改
     */

    // 插入项目
    @Override
    public int insertProject(ProjectEntity projectEntity) {
        return projectEntityMapper.insertProject(projectEntity);
    }

    //更新项目
    @Override
    public int updateProject(ProjectEntity projectEntity) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lastUpdateDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的最后更新时间
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

    //删除项目
    @Override
    public int deleteProjectById(String id) {
        return projectEntityMapper.deleteProjectById(id);
    }
}
