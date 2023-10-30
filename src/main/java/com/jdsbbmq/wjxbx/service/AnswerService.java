package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.answer.Answer;

import java.util.List;

public interface AnswerService {
    //分页寻找答卷
    List<Answer> selectAnswerByPage(QueryRequest queryRequest);

    //查询答卷总数
    Integer selectAnswerCount(String questionnaireId);

    //根据id查询答卷
    Answer selectAnswerById(String answerId);
}
