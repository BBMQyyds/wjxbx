package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {

    /*
        增删改
     */

    //增加

    //设计问卷问题
    public int insertDesignQuestion(DesignRequest designRequest);
}
