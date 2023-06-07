package com.jdsbbmq.wjxbx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
//    写一个RequestMapping,方法为post
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return "test";
    }

}
