package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserEntityMapper {

    /*
        功能实现
     */

    //查询

    // 登录
    UserEntity login(String username, String password);

    // 根据id查询用户
    UserEntity selectUserById(String id);

    // 查询所有用户
    List<UserEntity> selectAll();

    List<UserEntity> selectUserByPage(QueryEntity toQueryEntity);

    //增删改

    // 插入用户（注册）
    int insertUser(UserEntity userEntity);

    // 更新用户
    int updateUser(UserEntity userEntity);

    int disableUser(String id);

    int enableUser(String id);

    // 根据id删除用户
    int deleteUserById(String id);


    /*
        辅助功能
     */

    //获得登录密码
    String selectPasswordByUsername(String username);

    //修改登录密码
    int updatePassword(String username, String password);

    //清空status为0的用户
    int deleteUserByStatus();
}
