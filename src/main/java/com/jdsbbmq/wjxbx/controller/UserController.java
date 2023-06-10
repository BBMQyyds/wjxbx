package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.user.User;
import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import com.jdsbbmq.wjxbx.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Operation(summary = "登录接口", description = "输入username与password进行登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody String username, @RequestBody String password) {
        UserEntity userEntity = userService.login(username, password);
        return userEntity.toUser();
    }

    // 根据id查询用户


    @RequestMapping(value = "/selectUserById", method = RequestMethod.POST)
    @Operation(summary = "根据id查询用户", description = "根据id进行查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户信息"),
            @ApiResponse(responseCode = "404", description = "未找到对应的用户信息")
    })
    public User selectUserById(@RequestBody String id) {
        UserEntity userEntity = userService.selectUserById(id.substring(1, id.length() - 1));
        return userEntity.toUser();
    }

    // 查询所有用户
    @RequestMapping(value = "/selectAll", method = RequestMethod.POST)
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
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public int insertUser(@RequestBody @Valid User user) {
        return userService.insertUser(user.toUserEntity());
    }

    // 更新用户
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public int updateUser(@RequestBody @Valid User user) {
        return userService.updateUser(user.toUserEntity());
    }

    // 根据id删除用户
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    public int deleteUserById(@RequestBody String id) {
        return userService.deleteUserById(id);
    }
}
