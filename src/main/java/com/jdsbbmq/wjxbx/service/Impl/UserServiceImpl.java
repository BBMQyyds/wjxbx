package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.bean.user.User;
import com.jdsbbmq.wjxbx.dao.UserEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import com.jdsbbmq.wjxbx.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserEntityMapper userEntityMapper;

     /*
        查询
     */

    // 登录
    public User login(String username, String password) {
        return new User(userEntityMapper.login(username, password));
    }

    // 根据id查询用户
    public User selectUserById(String id) {
        return new User(userEntityMapper.selectUserById(id));
    }

    // 查询所有用户
    public List<User> selectAll() {
        List<UserEntity> userEntityList = userEntityMapper.selectAll();
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(new User(userEntity));
        }
        return userList;
    }

    /*
        增删改
     */

    // 插入用户（注册）
    public int insertUser(User user) {
        try {
            user.init();
            userEntityMapper.insertUser(new UserEntity(user));
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // 更新用户
    public int updateUser(User user) {
        return userEntityMapper.updateUser(new UserEntity(user));
    }

    // 根据id删除用户
    public int deleteUserById(String id) {
        return userEntityMapper.deleteUserById(id);
    }

    @Override
    public int changePassword(ChangeRequest changeRequest) {
        if (changeRequest.getOriginPassword().equals(userEntityMapper.selectPasswordByUsername(changeRequest.getUsername()))) {
            userEntityMapper.updatePassword(changeRequest.getUsername(), changeRequest.getNewPassword());
            return 1;
        } else {
            return 0;
        }
    }
}
