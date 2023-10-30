package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.user.ChangeRequest;
import com.jdsbbmq.wjxbx.bean.user.LoginRequest;
import com.jdsbbmq.wjxbx.bean.user.User;
import com.jdsbbmq.wjxbx.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    @Qualifier("asyncServiceExecutor")
    Executor executor;

     /*
        查询
     */

    // 登录
    @Operation(summary = "登录接口", description = "输入username与password进行登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody LoginRequest loginRequest) {
        CompletableFuture<User> user = CompletableFuture.supplyAsync(() ->
                userService.login(loginRequest.getUsername(), loginRequest.getPassword()), executor);
        return user.join();
    }


    // 根据id查询用户
    @RequestMapping(value = "/selectUserById", method = RequestMethod.POST)
    @Operation(summary = "根据id查询用户", description = "根据id进行查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户信息"),
            @ApiResponse(responseCode = "404", description = "未找到对应的用户信息")
    })
    public User selectUserById(@RequestBody String id) {
        CompletableFuture<User> user = CompletableFuture.supplyAsync(() ->
                userService.selectUserById(id), executor);
        return user.join();
    }

    // 查询所有用户
    @Operation(summary = "查询所有用户", description = "查询所有的用户信息")
    @RequestMapping(value = "/selectAll", method = RequestMethod.POST)
    public List<User> selectAll() {
        CompletableFuture<List<User>> userList = CompletableFuture.supplyAsync(() ->
                userService.selectAll(), executor);
        return userList.join();
    }

    //分页寻找用户
    @Operation(summary = "分页寻找用户", description = "能为用户分页寻找他的项目")
    @RequestMapping(value = "/selectUserByPage", method = RequestMethod.POST)
    public List<User> selectUserByPage(@RequestBody QueryRequest queryRequest) {
        CompletableFuture<List<User>> userList = CompletableFuture.supplyAsync(() ->
                userService.selectUserByPage(queryRequest), executor);
        return userList.join();
    }

    /*
        增删改
     */

    //增加
    // 插入用户(注册）
    @Operation(summary = "插入用户(注册)", description = "在获取账户的基础上，增加了注册时间、更新时间、状态等字段")
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public Integer insertUser(@RequestBody @Valid User user) {
        CompletableFuture<Integer> insertUser = CompletableFuture.supplyAsync(() ->
                userService.insertUser(user), executor);
        return insertUser.join();
    }

    //更新(修改）

    // 更新用户
    @Operation(summary = "更新用户", description = "更新某些用户的数据")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Integer updateUser(@RequestBody @Valid User user) {
        CompletableFuture<Integer> updateUser = CompletableFuture.supplyAsync(() ->
                userService.updateUser(user), executor);
        return updateUser.join();
    }

    //更新密码
    @Operation(summary = "更改密码", description = "已经有的用户进行更改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Integer updatePassword(@RequestBody ChangeRequest changeRequest) {
        CompletableFuture<Integer> updatePassword = CompletableFuture.supplyAsync(() ->
                userService.changePassword(changeRequest), executor);
        return updatePassword.join();
    }

    //禁用用户
    @Operation(summary = "禁用用户", description = "禁用用户")
    @RequestMapping(value = "/disableUser", method = RequestMethod.POST)
    public Integer disableUser(@RequestBody String id) {
        CompletableFuture<Integer> disableUser = CompletableFuture.supplyAsync(() ->
                userService.disableUser(id), executor);
        return disableUser.join();
    }

    //启用用户
    @Operation(summary = "启用用户", description = "启用用户")
    @RequestMapping(value = "/enableUser", method = RequestMethod.POST)
    public Integer enableUser(@RequestBody String id) {
        CompletableFuture<Integer> enableUser = CompletableFuture.supplyAsync(() ->
                userService.enableUser(id), executor);
        return enableUser.join();
    }

    //删除

    // 根据id删除用户
    @Operation(summary = "根据id删除用户", description = "根据id删除用户")
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    public Integer deleteUserById(@RequestBody String id) {
        CompletableFuture<Integer> deleteUserById = CompletableFuture.supplyAsync(() ->
                userService.deleteUserById(id), executor);
        return deleteUserById.join();
    }

    //清空status为0的用户
    @Operation(summary = "清空status为0的用户", description = "清空status为0的用户")
    @RequestMapping(value = "/deleteUserByStatus", method = RequestMethod.POST)
    public Integer deleteUserByStatus(@RequestBody String id) {
        CompletableFuture<Integer> deleteUserByStatus = CompletableFuture.supplyAsync(() ->
                userService.deleteUserByStatus(id), executor);
        return deleteUserByStatus.join();
    }

}
