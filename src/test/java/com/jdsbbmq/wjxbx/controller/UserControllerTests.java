package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.user.LoginRequest;
import com.jdsbbmq.wjxbx.bean.user.User;
import jakarta.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserControllerTests {

    Logger log = Logger.getLogger(UserControllerTests.class);
    @Resource
    private UserController userController;

    @Test
    void contextLoads() {

    }

    @Test
    // 登录
    public void loginTest() {
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername("卜部美琴");
        loginRequest.setPassword("0e82d06faf930d8c28b77d85ee3ec93e");
        //调用userMapper的方法
        User user = userController.login(loginRequest);
        if (user == null) {
            System.out.println("登录失败");
            // 记录error级别的信息
            log.error("UserController: >>login登录测试失败");
        } else {
            System.out.println(user);
            // 记录info级别的信息
            log.info("UserController: >>login登录测试成功");
        }
    }

    @Test
    // 查询所有用户
    public void selectAllTest() {
        //调用userMapper的方法
        List<User> list = userController.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("UserController: >>selectAll所有用户查询测试失败");
        } else {
            System.out.println(list);
            // 记录info级别的信息
            log.info("UserController: >>selectAll所有用户查询测试成功");
        }
    }

    @Test
    // 根据id查询用户
    public void selectUserByIdTest() {
        //调用userMapper的方法
        User user = userController.selectUserById("\"53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f\"");
        if (user == null) {
            System.out.println("查询失败");
            // 记录error级别的信息
            log.error("UserController: >>selectUser用户查询测试失败");
        } else {
            System.out.println(user);
            // 记录info级别的信息
            log.info("UserController: >>selectUser用户查询测试成功");
        }
    }

    @Test
    @Transactional
    // 插入用户
    public void insertUserTest() {
        //调用userMapper的方法
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setStatus(1);
        user.setUsername("LS");
        user.setPassword("123");
        int i = userController.insertUser(user);
        if (i == 0) {
            System.out.println("插入失败");
            // 记录error级别的信息
            log.error("UserController: >>insertUser用户插入测试失败");
        } else {
            System.out.println(i);
            // 记录info级别的信息
            log.info("UserController: >>insertUser用户插入测试成功");
        }
    }

    @Test
    @Transactional
    // 更新用户
    public void updateUserByIdTest() {
        //调用userMapper的方法
        User user = new User();
        user.setId("216c6b4f-12e2-4012-a029-37b15c965547");
        user.setStatus(1);
        user.setUsername("LS");
        user.setPassword("123");
        int i = userController.updateUser(user);
        if (i == 0) {
            System.out.println("更新失败");
            // 记录error级别的信息
            log.error("UserController: >>updateUser用户更新测试失败");
        } else {
            System.out.println(i);
            // 记录info级别的信息
            log.info("UserController: >>updateUser用户更新测试成功");
        }
    }

    @Test
    @Transactional
    // 根据id删除用户
    public void deleteUserByIdTest() {
        //调用userMapper的方法
        int i = userController.deleteUserById("4f48ba43-e933-4cef-ac7d-094e1e70c555");
        if (i == 0) {
            System.out.println("删除失败");
            // 记录error级别的信息
            log.error("UserController: >>deleteUser用户删除测试失败");
        } else {
            System.out.println(i);
            // 记录info级别的信息
            log.info("UserController: >>deleteUser用户删除测试成功");
        }
    }

}
