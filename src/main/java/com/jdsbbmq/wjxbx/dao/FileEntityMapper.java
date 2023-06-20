package com.jdsbbmq.wjxbx.dao;

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

    //增加
    public int insertFile(FileEntity fileEntity);

    //更新
    public int updateFile(FileEntity fileEntity);

    //删除
    public int deleteFile(String id);
}
