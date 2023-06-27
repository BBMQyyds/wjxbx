package com.jdsbbmq.wjxbx.service.Impl;

import com.google.gson.Gson;
import com.jdsbbmq.wjxbx.bean.question.AnswerRequest;
import com.jdsbbmq.wjxbx.bean.question.DesignRequest;
import com.jdsbbmq.wjxbx.bean.question.Question;
import com.jdsbbmq.wjxbx.bean.question.UpdateQuestionStarRequest;
import com.jdsbbmq.wjxbx.dao.QuestionEntityMapper;
import com.jdsbbmq.wjxbx.dao.QuestionnaireEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.AnswerEntity;
import com.jdsbbmq.wjxbx.dao.entity.QuestionEntity;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import com.jdsbbmq.wjxbx.service.QuestionService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionEntityMapper questionEntityMapper;

    @Resource
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    /*
        查询
     */
    //根据所给的问卷Id，查找其设计的问题
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<List<Question>> selectQuestionById(String id) {
        Gson gson = new Gson();
        List<QuestionEntity> questionEntityList = questionEntityMapper.selectQuestionById(id);
        List<Question> questionList = new ArrayList<>();
        for (QuestionEntity questionEntity : questionEntityList) {
            Question question = gson.fromJson(questionEntity.getQuestionContent(), Question.class);
            question.setStar(questionEntity.getStar());
            questionList.add(question);
        }
        return CompletableFuture.completedFuture(questionList);
    }

    //查询个人题库中的所有问题
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<List<Question>> selectPrivateQuestion(String userId) {
        Gson gson = new Gson();
        List<Question> questionList = new ArrayList<>();
        List<QuestionEntity> questionEntityList = questionEntityMapper.selectPrivateQuestion(userId);
        for (QuestionEntity questionEntity : questionEntityList) {
            Question question = gson.fromJson(questionEntity.getQuestionContent(), Question.class);
            question.setStar(1);
            questionList.add(question);
        }
        return CompletableFuture.completedFuture(questionList);
    }

    //查询所有答卷
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<List<Question>> selectAllAnswer(String questionnaireId) {
            Gson gson = new Gson();
            List<AnswerEntity> answerEntityList = questionEntityMapper.selectAllAnswer(questionnaireId);
            List<Question> questionList = new ArrayList<>();
            for (AnswerEntity answerEntity : answerEntityList) {
                Question question = gson.fromJson(answerEntity.getQuestionnaireContent(), Question.class);
                questionList.add(question);
            }
            return CompletableFuture.completedFuture(questionList);
    }


        /*
            增删改
        */

    //增加

    //设计问卷问题
    @Override
    @Async("asyncServiceExecutor")
    @Transactional(rollbackFor = RuntimeException.class)
    public CompletableFuture<Integer> insertDesignQuestion(DesignRequest designRequest) {
        try {
            Gson gson = new Gson();
            List<QuestionEntity> questionEntityList = new ArrayList<>();
            for (int i = 0; i < designRequest.getQuestions().size(); i++) {
                QuestionEntity questionEntity = new QuestionEntity(designRequest.getId(), designRequest.getQuestions().get(i).getQuestionId(), i + 1, designRequest.getQuestions().get(i).getStar(), gson.toJson(designRequest.getQuestions().get(i)));
                questionEntityList.add(questionEntity);
            }
            if (questionEntityList.size() == 0) {
                questionEntityMapper.deleteQuestionNotInList(questionEntityList);
                return CompletableFuture.completedFuture(1);
            }

            QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
            questionnaireEntity.setQuestionCount(questionEntityList.size());
            questionnaireEntity.setId(designRequest.getId());
            questionEntityMapper.updateQuestionnaireQuestionCount(questionnaireEntity);
            questionEntityMapper.deleteQuestionNotInList(questionEntityList);
            questionEntityMapper.insertDesignQuestion(questionEntityList);

            questionEntityList=questionEntityMapper.selectAllPrivateQuestion(designRequest.getId());
            for (QuestionEntity questionEntity:questionEntityList){
                questionEntityMapper.updatePrivateQuestion(questionEntity);
            }
            return CompletableFuture.completedFuture(1);
        } catch (Exception e) {
            throw new RuntimeException("插入设计问卷的问题失败");
        }
    }

    @Override
    @Async("asyncServiceExecutor")
    @Transactional(rollbackFor = RuntimeException.class)
    public CompletableFuture<Integer> insertAnswer(AnswerRequest answerRequest) {
        try{
            Gson gson=new Gson();
            AnswerEntity answerEntity= new AnswerEntity(java.util.UUID.randomUUID().toString(),answerRequest.getUserId(),answerRequest.getQuestionnaireId(),gson.toJson(answerRequest.getQuestions()));
            int a=questionEntityMapper.insertAnswer(answerEntity);
            int b=questionnaireEntityMapper.updateOnAnswerCount(answerRequest.getQuestionnaireId());
            if(a!=1||b!=1){
                throw new RuntimeException("插入答卷失败");
            }
            return CompletableFuture.completedFuture(1);
        } catch (Exception e) {
            throw new RuntimeException("插入设计问卷的问题失败");
        }
    }

    //将问卷问题放入个人题库
    @Override
    @Async("asyncServiceExecutor")
    @Transactional(rollbackFor = RuntimeException.class)
    public CompletableFuture<Integer> insertPrivateQuestion(UpdateQuestionStarRequest updateQuestionStarRequest) {
        try {
            String questionContent = questionEntityMapper.selectQuestionContentById(updateQuestionStarRequest.getQuestionId());
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setId(updateQuestionStarRequest.getUserId());
            questionEntity.setQuestionId(updateQuestionStarRequest.getQuestionId());
            questionEntity.setQuestionContent(questionContent);
            int a = questionEntityMapper.insertPrivateQuestion(questionEntity);
            int b = questionEntityMapper.updateStarOnQuestion(updateQuestionStarRequest.getQuestionId());
            if (a != 1 || questionContent == null || b != 1) {
                throw new RuntimeException("插入个人题库失败");
            }
            return CompletableFuture.completedFuture(1);
        } catch (Exception e) {
            throw new RuntimeException("插入个人题库失败");
        }
    }

    //删除

    //将问卷问题从个人题库中拿出
    @Override
    @Async("asyncServiceExecutor")
    @Transactional(rollbackFor = RuntimeException.class)
    public CompletableFuture<Integer> deletePrivateQuestionById(String questionId) {
        try {
            int a = questionEntityMapper.deletePrivateQuestionById(questionId);
            int b = questionEntityMapper.updateStarOffQuestion(questionId);
            if (a != 1 || b != 1) {
                throw new RuntimeException("删除个人题库失败");
            }
            return CompletableFuture.completedFuture(1);
        } catch (Exception e) {
            throw new RuntimeException("删除个人题库失败");
        }
    }
}
