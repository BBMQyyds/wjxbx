package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.project.Project;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface ProjectService {
     /*
        查询
     */

    //查询用户下方所有项目
    public List<Project> selectAllProject(String userId) throws ParseException;

    //根据id查询项目
    public Project selectProjectById(String id) throws ParseException;

    //根据projectName查询项目
    public List<Project> selectProjectByName(String projectName) throws ParseException;

    //分页寻找项目
    public List<Project> selectProjectByPage(QueryRequest queryRequest) throws ParseException;
    /*
        增删改
     */

    // 插入项目
    public Integer insertProject(Project project);

    //更新项目
    public Integer updateProject(Project project) throws ParseException;

    //将项目的收藏更新为已收藏
    public Integer updateStarOnProject(String id);

    //将项目的收藏更新为未收藏
    public Integer updateStarOffProject(String id);

    //将项目的删除更新为已删除
    public Integer updateDeletedOnProject(String id);

    //将项目的删除更新为未删除
    public Integer updateDeletedOffProject(String id);

    // 删除项目
    public Integer deleteProjectById(String id);

    //清空回收站
    public Integer deleteAllProjectRecycled(String userId);

    //复制项目
    Integer insertCopyQuestionnaire(String id) throws ParseException;
}
