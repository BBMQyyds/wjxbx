package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserEntityMapper {

    // 登录
    UserEntity login(@Param("username") String username, @Param("password") String password);
    List<UserEntity> selectAll();
    UserEntity selectUser(String id);
    int insertUser(UserEntity userEntity);
    int updateUser(UserEntity userEntity);
    int deleteUser(String id);
}
