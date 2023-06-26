package com.jdsbbmq.wjxbx.service;


import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.bean.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface UserService {

    /*
        查询
     */

    // 登录
    CompletableFuture<User> login(String username, String password);

    // 根据id查询用户
    CompletableFuture<User> selectUserById(String id);

    // 查询所有用户
    CompletableFuture<List<User>> selectAll();

    /*
        增删改
     */

    //增加

    // 插入用户（注册）
    CompletableFuture<Integer> insertUser(User user);


    //更新（修改）
    // 更新用户
    CompletableFuture<Integer> updateUser(User user);

    //修改登录密码
    CompletableFuture<Integer> changePassword(ChangeRequest changeRequest);

    //删除

    // 根据id删除用户
    CompletableFuture<Integer> deleteUserById(String id);


}
