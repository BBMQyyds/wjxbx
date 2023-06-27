package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.answer.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mapper
@Repository
public interface AnswerEntityMapper {

    /*
       查询
    */

    //分页寻找答卷
    CompletableFuture<List<Answer>> selectAnswerByPage(QueryRequest queryRequest);

    /*
       增删改
    */

    /*
       辅助功能
    */
}
