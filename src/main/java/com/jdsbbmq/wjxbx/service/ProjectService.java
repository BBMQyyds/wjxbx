package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.project.Project;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface ProjectService {
     /*
        查询
     */

    //查询用户下方所有项目
    public CompletableFuture<List<Project>> selectAllProject(String userId);

    //根据id查询项目
    public CompletableFuture<Project> selectProjectById(String id);

    //根据projectName查询项目
    public CompletableFuture<List<Project>> selectProjectByName(String projectName);

    //分页寻找项目
    public CompletableFuture<List<Project>> selectProjectByPage(QueryRequest queryRequest);
    /*
        增删改
     */

    // 插入项目
    public CompletableFuture<Integer> insertProject(Project project);

    //更新项目
    public CompletableFuture<Integer> updateProject(Project project) throws ParseException;

    //将项目的收藏更新为已收藏
    public CompletableFuture<Integer> updateStarOnProject(String id);

    //将项目的收藏更新为未收藏
    public CompletableFuture<Integer> updateStarOffProject(String id);

    //将项目的删除更新为已删除
    public CompletableFuture<Integer> updateDeletedOnProject(String id);

    //将项目的删除更新为未删除
    public CompletableFuture<Integer> updateDeletedOffProject(String id);

    // 删除项目
    public CompletableFuture<Integer> deleteProjectById(String id);

    //清空回收站
    public CompletableFuture<Integer> deleteAllProjectRecycled(String userId);
}
