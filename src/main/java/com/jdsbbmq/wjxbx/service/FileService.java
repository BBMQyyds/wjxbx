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
    public int insertFile(File file);

    //复制一个文件夹
    public int insertCopyFile(File file);

    //修改（更新）

    //修改一个文件夹的名字
    public int updateFile(File file);
}
