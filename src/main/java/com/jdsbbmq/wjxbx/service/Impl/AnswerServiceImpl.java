package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.answer.Answer;
import com.jdsbbmq.wjxbx.dao.AnswerEntityMapper;
import com.jdsbbmq.wjxbx.service.AnswerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerEntityMapper answerEntityMapper;

    //分页寻找答卷
    @Override
    public CompletableFuture<List<Answer>> selectAnswerByPage(QueryRequest queryRequest) {
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
        return answerEntityMapper.selectAnswerByPage(queryRequest);
    }
}
