package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.file.File;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    /*
        查询
     */

    /*
        增删改
     */

    //增加（插入）

    //插入一个文件夹
    public Integer insertFile(File file);

    //复制一个文件夹
    public Integer insertCopyFile(File file);

    //修改（更新）

    //修改一个文件夹的名字
    public Integer updateFile(File file);

    //删除

    //删除一个文件夹
    public Integer deleteFile(String fileId);
}
