package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.dao.UserEntityMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Resource
    UserEntityMapper userEntityMapper;
    @RequestMapping("/test")
    public String test(@RequestBody String username) {
        userEntityMapper.selectIdByUsername(username);
        return "test";
    }

}