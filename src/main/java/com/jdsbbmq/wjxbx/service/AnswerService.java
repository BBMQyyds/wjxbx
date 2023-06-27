package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.answer.Answer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AnswerService {
    //分页寻找答卷
    CompletableFuture<List<Answer>> selectAnswerByPage(QueryRequest queryRequest);
}
