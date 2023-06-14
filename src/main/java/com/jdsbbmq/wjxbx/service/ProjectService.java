package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProjectService {
     /*
        查询
     */

    //查询用户下方所有项目
    public List<ProjectEntity> selectAllProject(String userId);

    //根据id查询项目
    public ProjectEntity selectProjectById(String id);

    //根据projectName查询项目
    public List<ProjectEntity> selectProjectByName(String projectName);

    /*
        增删改
     */

    // 插入项目
    public int insertProject(ProjectEntity projectEntity);

    //更新项目
    public int updateProject(ProjectEntity projectEntity);

    // 删除项目
    public int deleteProjectById(String id);
}
