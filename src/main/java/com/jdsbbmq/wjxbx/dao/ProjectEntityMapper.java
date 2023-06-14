package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
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

    /*
        增删改
     */

    // 插入项目
    int insertProject(ProjectEntity projectEntity);

    // 更新项目
    int updateProject(ProjectEntity projectEntity);

    // 根据id删除项目
    int deleteProjectById(String id);


    /*
        辅助功能
     */
}
