package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.dao.entity.AttributionEntity;
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

    //增加

    //增加一个新的父子关系与关系库中
    public int insertAttribution(AttributionEntity attributionEntity);

    //复制某父结点下的所有子结点到新的父结点下
    public int insertCopyAttribution(AttributionEntity attributionEntity);

    //更新(修改)

    //将原先父节点的子节点放到新的父节点上
    public int updateAttributionOnParent(AttributionEntity attributionEntity);

    //删除

    //将父节点删除时，原带所有的子结点一起删除
    public int deleteAttributionOnParent(String id);
}
