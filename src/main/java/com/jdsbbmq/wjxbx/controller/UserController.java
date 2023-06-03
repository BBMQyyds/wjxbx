package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.user.User;
import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import com.jdsbbmq.wjxbx.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userService;

     /*
        查询
     */

    // 登录
    @RequestMapping("/login")
    public User login(@RequestBody String username, @RequestBody String password) {
        UserEntity userEntity = userService.login(username, password);
        return userEntity.toUser();
    }

    // 根据id查询用户
    @RequestMapping("/selectUserById")
    public User selectUserById(@RequestBody String id) {
        UserEntity userEntity = userService.selectUserById(id);
        return userEntity.toUser();
    }

    // 查询所有用户
    @RequestMapping("/selectAll")
    public List<User> selectAll() {
        List<UserEntity> userEntityList = userService.selectAll();
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(userEntity.toUser());
        }
        return userList;
    }

    /*
        增删改
     */

    // 插入用户（注册）
    @RequestMapping("/insertUser")
    public int insertUser(@RequestBody @Valid User user) {
        return userService.insertUser(user.toUserEntity());
    }

    // 更新用户
    @RequestMapping("/updateUser")
    public int updateUser(@RequestBody @Valid User user) {
        return userService.updateUser(user.toUserEntity());
    }

    // 根据id删除用户
    @RequestMapping("/deleteUserById")
    public int deleteUserById(@RequestBody String id) {
        return userService.deleteUserById(id);
    }
}
