package com.jdsbbmq.wjxbx.service;


import com.jdsbbmq.wjxbx.bean.QueryRequest;
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
    User login(String username, String password);

    // 根据id查询用户
    User selectUserById(String id);

    // 查询所有用户
    List<User> selectAll();

    /*
        增删改
     */

    //增加

    // 插入用户（注册）
    Integer insertUser(User user);


    //更新（修改）
    // 更新用户
    Integer updateUser(User user);

    //修改登录密码
    Integer changePassword(ChangeRequest changeRequest);

    //删除

    // 根据id删除用户
    Integer deleteUserById(String id);

    //分页寻找用户
    List<User> selectUserByPage(QueryRequest queryRequest);

    //禁用用户
    Integer disableUser(String id);

    //启用用户
    Integer enableUser(String id);

    //清空status为0的用户
    Integer deleteUserByStatus(String id);
}
