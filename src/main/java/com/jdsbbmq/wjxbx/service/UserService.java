package com.jdsbbmq.wjxbx.service;


import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.bean.user.User;
import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    int insertUser(User user);


    //更新（修改）
    // 更新用户
    int updateUser(User user);

    //修改登录密码
    int changePassword(ChangeRequest changeRequest);

    //删除

    // 根据id删除用户
    int deleteUserById(String id);


}
