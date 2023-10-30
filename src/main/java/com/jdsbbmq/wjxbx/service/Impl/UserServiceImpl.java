package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
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
    @Override
    public User login(String username, String password) {
        return new User(userEntityMapper.login(username, password));
    }

    // 根据id查询用户
    @Override
    public User selectUserById(String id) {
        return new User(userEntityMapper.selectUserById(id));
    }

    // 查询所有用户
    @Override
    public List<User> selectAll() {
        List<UserEntity> userEntityList = userEntityMapper.selectAll();
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(new User(userEntity));
        }
        return userList;
    }

    //分页寻找用户
    @Override
    public List<User> selectUserByPage(QueryRequest queryRequest) {
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
        List<UserEntity> userEntityList = userEntityMapper.selectUserByPage(queryRequest.ToQueryEntity());
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
    @Override
    public Integer insertUser(User user) {
        try {
            user.init();
            userEntityMapper.insertUser(new UserEntity(user));
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // 更新用户
    @Override
    public Integer updateUser(User user) {
        return userEntityMapper.updateUser(new UserEntity(user));
    }

    @Override
    public Integer changePassword(ChangeRequest changeRequest) {
        if (changeRequest.getOriginPassword().equals(userEntityMapper.selectPasswordByUsername(changeRequest.getUsername()))) {
            userEntityMapper.updatePassword(changeRequest.getUsername(), changeRequest.getNewPassword());
            return 1;
        } else {
            return 0;
        }
    }

    //禁用用户
    @Override
    public Integer disableUser(String id) {
        return userEntityMapper.disableUser(id);
    }

    //启用用户
    @Override
    public Integer enableUser(String id) {
        return userEntityMapper.enableUser(id);
    }

    // 根据id删除用户
    @Override
    public Integer deleteUserById(String id) {
        return userEntityMapper.deleteUserById(id);
    }

    //清空status为0的用户
    @Override
    public Integer deleteUserByStatus(String id) {
        UserEntity userEntity = userEntityMapper.selectUserById(id);
        if (userEntity.getUsername().equals("admin")) {
            return userEntityMapper.deleteUserByStatus();
        } else {
            return 0;
        }
    }
}
