package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.dao.UserEntityMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}