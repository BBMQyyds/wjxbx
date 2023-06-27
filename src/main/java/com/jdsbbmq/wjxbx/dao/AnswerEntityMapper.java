package com.jdsbbmq.wjxbx.dao;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.answer.Answer;
import com.jdsbbmq.wjxbx.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerEntityMapper {

    /*
       查询
    */

    //分页寻找答卷
    List<AnswerEntity> selectAnswerByPage(QueryRequest queryRequest);

    //查询答卷总数
    int selectAnswerCount(String questionnaireId);

    //根据id查询答卷
    AnswerEntity selectAnswerById(String answerId);


    /*
       增删改
    */
    //增加答卷
    public int insertAnswer(AnswerEntity answerEntity);

    /*
       辅助功能
    */
}
