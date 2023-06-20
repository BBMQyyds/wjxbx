package com.jdsbbmq.wjxbx.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    //    写一个RequestMapping,方法为post
    @Operation(summary = "测试接口", description = "一个随便的测试")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return "test";
    }

}
