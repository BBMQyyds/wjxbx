package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.bean.file.File;
import com.jdsbbmq.wjxbx.dao.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileEntityMapper {
        /*
           查询
        */




        /*
           增删改
        */
    public int insertFile(FileEntity fileEntity);
}
