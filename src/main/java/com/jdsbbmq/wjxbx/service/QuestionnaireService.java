package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface QuestionnaireService {
    /*
        查询
     */

    //查找一个项目下的所有问卷
    public CompletableFuture<List<Questionnaire>> selectAllQuestionnaire(String projectId);

    //查找一个问卷
    public CompletableFuture<Questionnaire> selectQuestionnaireById(String questionnaireId);

    //分页查找问卷
    public CompletableFuture<List<Questionnaire>> selectQuestionnaireByPage(QueryRequest queryRequest);

     /*
        增删改
     */

    //增加

    //插入一个问卷
    public CompletableFuture<Integer> insertQuestionnaire(Questionnaire questionnaire);

    //修改更新

    //更新一个问卷
    public CompletableFuture<Integer> updateQuestionnaire(Questionnaire questionnaire);

    //更新问卷的收藏状态
    public CompletableFuture<Integer> updateStarOnQuestionnaire(String questionnaireId);

    //更新取消问卷的收藏状态
    public CompletableFuture<Integer> updateStarOffQuestionnaire(String questionnaireId);

    //更新问卷的删除状态
    public CompletableFuture<Integer> updateDeletedOnQuestionnaire(String questionnaireId);

    //更新取消问卷的删除状态
    public CompletableFuture<Integer> updateDeletedOffQuestionnaire(String questionnaireId);

    //发布问卷
    public CompletableFuture<Integer> updateReleaseQuestionnaire(String questionnaireId);

    //收回问卷
    public CompletableFuture<Integer> updateReclaimQuestionnaire(String questionnaireId);

    //删除

    //删除一个问卷
    public CompletableFuture<Integer> deleteQuestionnaireById(String questionnaireId);

    //清空回收站
    public CompletableFuture<Integer> deleteAllQuestionnaireRecycled(String projectId);
}
