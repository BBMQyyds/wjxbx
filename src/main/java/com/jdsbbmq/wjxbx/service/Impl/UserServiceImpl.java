package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.dao.UserEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import com.jdsbbmq.wjxbx.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserEntityMapper userEntityMapper;

     /*
        查询
     */

    // 登录
    public UserEntity login(String username, String password) {
        return userEntityMapper.login(username, password);
    }

    // 根据id查询用户
    public UserEntity selectUserById(String id) {
        return userEntityMapper.selectUserById(id);
    }

    // 查询所有用户
    public List<UserEntity> selectAll() {
        return userEntityMapper.selectAll();
    }

    /*
        增删改
     */

    // 插入用户（注册）
    public int insertUser(UserEntity userEntity) {
        return userEntityMapper.insertUser(userEntity);
    }

    // 更新用户
    public int updateUser(UserEntity userEntity) {
        return userEntityMapper.updateUser(userEntity);
    }

    // 根据id删除用户
    public int deleteUserById(String id) {
        return userEntityMapper.deleteUserById(id);
    }

    @Override
    public int changePassword(ChangeRequest changeRequest) {
        if(changeRequest.getOriginPassword().equals(userEntityMapper.selectPasswordByUsername(changeRequest.getUsername()))){
            userEntityMapper.updatePassword(changeRequest.getUsername(),changeRequest.getNewPassword());
            return 1;
        }else{
            return 0;
        }
    }
}
