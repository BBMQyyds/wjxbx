package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.collect.Collect;
import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.bean.user.LoginRequest;
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

import java.text.ParseException;
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
    public User login(@RequestBody LoginRequest loginRequest) {
        UserEntity userEntity = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new User(userEntity);
    }


    // 根据id查询用户
    @RequestMapping(value = "/selectUserById", method = RequestMethod.POST)
    @Operation(summary = "根据id查询用户", description = "根据id进行查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户信息"),
            @ApiResponse(responseCode = "404", description = "未找到对应的用户信息")
    })
    public User selectUserById(@RequestBody String id) {
        UserEntity userEntity = userService.selectUserById(id);
        return new User(userEntity);
    }

    // 查询所有用户
    @Operation(summary = "查询所有用户", description = "查询所有的用户信息")
    @RequestMapping(value = "/selectAll", method = RequestMethod.POST)
    public List<User> selectAll() {
        List<UserEntity> userEntityList = userService.selectAll();
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(new User(userEntity));
        }
        return userList;
    }

    /*
        增删改
     */

    //增加

    // 插入用户(注册）
    @Operation(summary = "插入用户(注册)", description = "在获取账户的基础上，增加了注册时间、更新时间、状态等字段")
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public int insertUser(@RequestBody @Valid User user) throws ParseException {
        user.init();
        return userService.insertUser(new UserEntity(user));
    }

    //插入收藏项目
    @Operation(summary = "插入收藏项目", description = "往星标项目表中加入项目")
    @RequestMapping(value = "/insertCollectProject", method = RequestMethod.POST)
    public int insertCollectProject(@RequestBody Collect addCollectRequest) throws ParseException {
        return userService.insertCollectProject(addCollectRequest);
    }


    //更新(修改）

    // 更新用户
    @Operation(summary = "更新用户", description = "更新某些用户的数据")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public int updateUser(@RequestBody @Valid User user) {
        return userService.updateUser(new UserEntity(user));
    }

    //更新密码
    @Operation(summary = "更改密码", description = "已经有的用户进行更改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public int updatePassword(@RequestBody ChangeRequest changeRequest) {
        return userService.changePassword(changeRequest);
    }

    //删除

    // 根据id删除用户
    @Operation(summary = "根据id删除用户", description = "根据id删除用户")
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    public int deleteUserById(@RequestBody String id) {
        return userService.deleteUserById(id);
    }


}
