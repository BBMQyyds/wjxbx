package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.bean.user.User;
import com.jdsbbmq.wjxbx.dao.UserEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import com.jdsbbmq.wjxbx.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserEntityMapper userEntityMapper;

     /*
        查询
     */

    // 登录
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<User> login(String username, String password) {
        return CompletableFuture.completedFuture(new User(userEntityMapper.login(username, password)));
    }

    // 根据id查询用户
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<User> selectUserById(String id) {
        return CompletableFuture.completedFuture(new User(userEntityMapper.selectUserById(id)));
    }

    // 查询所有用户
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<List<User>> selectAll() {
        List<UserEntity> userEntityList = userEntityMapper.selectAll();
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(new User(userEntity));
        }
        return CompletableFuture.completedFuture(userList);
    }

    //分页寻找用户
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<List<User>> selectUserByPage(QueryRequest queryRequest) {
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
        List<UserEntity> userEntityList = userEntityMapper.selectUserByPage(queryRequest.ToQueryEntity());
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(new User(userEntity));
        }
        return CompletableFuture.completedFuture(userList);
    }

    /*
        增删改
     */

    // 插入用户（注册）
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> insertUser(User user) {
        try {
            user.init();
            userEntityMapper.insertUser(new UserEntity(user));
            return CompletableFuture.completedFuture(1);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(0);
        }
    }

    // 更新用户
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> updateUser(User user) {
        return CompletableFuture.completedFuture(userEntityMapper.updateUser(new UserEntity(user)));
    }

    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> changePassword(ChangeRequest changeRequest) {
        if (changeRequest.getOriginPassword().equals(userEntityMapper.selectPasswordByUsername(changeRequest.getUsername()))) {
            userEntityMapper.updatePassword(changeRequest.getUsername(), changeRequest.getNewPassword());
            return CompletableFuture.completedFuture(1);
        } else {
            return CompletableFuture.completedFuture(0);
        }
    }

    //禁用用户
    @Override
    @Async
    public CompletableFuture<Integer> disableUser(String id) {
        return CompletableFuture.completedFuture(userEntityMapper.disableUser(id));
    }

    //启用用户
    @Override
    @Async
    public CompletableFuture<Integer> enableUser(String id) {
        return CompletableFuture.completedFuture(userEntityMapper.enableUser(id));
    }

    // 根据id删除用户
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> deleteUserById(String id) {
        return CompletableFuture.completedFuture(userEntityMapper.deleteUserById(id));
    }

    //清空status为0的用户
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> deleteUserByStatus(String id) {
        UserEntity userEntity = userEntityMapper.selectUserById(id);
        if (userEntity.getUsername().equals("admin")) {
            return CompletableFuture.completedFuture(userEntityMapper.deleteUserByStatus());
        } else {
            return CompletableFuture.completedFuture(0);
        }
    }
}
