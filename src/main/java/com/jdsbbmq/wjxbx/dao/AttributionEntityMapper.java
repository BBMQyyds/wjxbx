package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.AttributionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AttributionEntityMapper {
        /*
           查询
        */





        /*
           增删改
        */
    public int insertAttributionEntity(AttributionEntity attributionEntity);
}
