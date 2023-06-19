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
    public int insertFile(File file);
}
