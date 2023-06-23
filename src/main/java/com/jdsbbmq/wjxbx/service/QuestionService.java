package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.bean.question.Question;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface QuestionService {
    /*
        查询
     */
    //根据所给的问卷Id，查找其设计的问题
    public List<Question> selectQuestionById(String id);


    /*
        增删改
     */

    //增加

    //设计问卷问题
    public int insertDesignQuestion(DesignRequest designRequest);
}
