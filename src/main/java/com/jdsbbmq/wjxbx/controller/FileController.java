package com.jdsbbmq.wjxbx.controller;

import com.jdsbbmq.wjxbx.bean.file.File;
import com.jdsbbmq.wjxbx.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    @Resource
    private FileService fileService;

    /*
        查询
     */



    /*
        增删改
     */
    @Operation(summary = "插入一个文件夹", description = "插入一个文件夹")
    @RequestMapping(value = "/insertFile" ,method = RequestMethod.POST)
    public int insertFile(@RequestBody File file) {
        return fileService.insertFile(file);
    }
}
