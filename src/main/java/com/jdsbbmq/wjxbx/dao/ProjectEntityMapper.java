package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectEntityMapper {

     /*
        功能实现
     */

    //查询

    // 查询所有项目
    List<ProjectEntity> selectAllProject(String userId);

    // 根据id查询项目
    ProjectEntity selectProjectById(String id);

    //根据名称查询项目
    List<ProjectEntity> selectProjectByName(String username);

    //分页查询项目
    List<ProjectEntity> selectProjectByPage(QueryEntity queryEntity);

    /*
        增删改
     */


    //增加

    // 插入项目
    int insertProject(ProjectEntity projectEntity);

    //更新

    // 更新项目
    int updateProject(ProjectEntity projectEntity);

    // 将项目的收藏更新为已收藏
    int updateStarOnProject(String id);

    // 将项目的收藏更新为未收藏
    int updateStarOffProject(String id);

    // 将项目的删除更新为已删除
    int updateDeletedOnProject(String id);

    // 将项目的删除更新为未删除
    int updateDeletedOffProject(String id);
    //删除

    // 根据id删除项目
    int deleteProjectById(String id);

    int deleteAllProjectRecycled(String userId);

    /*
        辅助功能
     */

    //增加项目的问卷数量
    int addProjectQuestionnaireCount(String id);

    //减少项目的问卷数量
    int reduceProjectQuestionnaireCount(String id);

    //清空回收站时减少项目的问卷数量
    int reduceProjectQuestionnaireCountRecycled(String projectId);
}
