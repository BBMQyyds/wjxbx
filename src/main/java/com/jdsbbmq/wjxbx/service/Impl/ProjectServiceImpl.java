package com.jdsbbmq.wjxbx.service.Impl;

import com.jdsbbmq.wjxbx.bean.QueryRequest;
import com.jdsbbmq.wjxbx.bean.project.Project;
import com.jdsbbmq.wjxbx.dao.ProjectEntityMapper;
import com.jdsbbmq.wjxbx.dao.QuestionEntityMapper;
import com.jdsbbmq.wjxbx.dao.QuestionnaireEntityMapper;
import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import com.jdsbbmq.wjxbx.dao.entity.QuestionEntity;
import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import com.jdsbbmq.wjxbx.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectEntityMapper projectEntityMapper;

    @Resource
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    @Resource
    private QuestionEntityMapper questionEntityMapper;


      /*
        查询
     */

    //查询该用户下的所有项目
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<List<Project>> selectAllProject(String userId) throws ParseException {
        List<ProjectEntity> projectEntityList = projectEntityMapper.selectAllProject(userId);
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(new Project(projectEntity));
        }
        return CompletableFuture.completedFuture(projectList);
    }

    //根据id查询项目
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Project> selectProjectById(String id) throws ParseException {
        return CompletableFuture.completedFuture(new Project(projectEntityMapper.selectProjectById(id)));
    }

    //根据projectName查询项目
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<List<Project>> selectProjectByName(String projectName) throws ParseException {
        List<ProjectEntity> projectEntityList = projectEntityMapper.selectProjectByName(projectName);
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(new Project(projectEntity));
        }
        return CompletableFuture.completedFuture(projectList);
    }

    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<List<Project>> selectProjectByPage(QueryRequest queryRequest) throws ParseException {
        queryRequest.setOffset((queryRequest.getCurrentPage() - 1) * queryRequest.getPageSize());
        List<ProjectEntity> projectEntityList = projectEntityMapper.selectProjectByPage(queryRequest.ToQueryEntity());
        List<Project> projectList = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntityList) {
            projectList.add(new Project(projectEntity));
        }
        return CompletableFuture.completedFuture(projectList);
    }


    /*
        增删改
     */

    // 插入项目
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> insertProject(Project project) {
        try {
            project.init();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ProjectEntity projectEntity = new ProjectEntity(project);
        return CompletableFuture.completedFuture(projectEntityMapper.insertProject(projectEntity));
    }

    // 复制项目
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> insertCopyQuestionnaire(String id) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ProjectEntity projectEntity = projectEntityMapper.selectProjectById(id);
        projectEntity.setId(UUID.randomUUID().toString());
        projectEntity.setProjectName(projectEntity.getProjectName() + " - 副本");
        projectEntity.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));
        projectEntity.setLastUpdateDate(dateFormat.parse(dateFormat.format(new Date())));
        projectEntityMapper.insertProject(projectEntity);
        List<QuestionnaireEntity> listQuestionnaire = questionnaireEntityMapper.selectAllQuestionnaire(id);
        for (QuestionnaireEntity questionnaireEntity : listQuestionnaire) {
            String originalQuestionnaireId = questionnaireEntity.getId();
            questionnaireEntity.setId(UUID.randomUUID().toString());
            questionnaireEntity.setProjectId(projectEntity.getId());
            questionnaireEntity.setQuestionnaireName(questionnaireEntity.getQuestionnaireName());
            questionnaireEntity.setStartTime(null);
            questionnaireEntity.setEndTime(null);
            questionnaireEntity.setAnswerCount(0);
            questionnaireEntity.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));
            questionnaireEntityMapper.insertQuestionnaire(questionnaireEntity);
            List<QuestionEntity> listQuestion = questionEntityMapper.selectQuestionById(originalQuestionnaireId);
            for (QuestionEntity questionEntity : listQuestion) {
                questionEntity.setQuestionId(UUID.randomUUID().toString());
                questionEntity.setId(questionnaireEntity.getId());
                questionEntity.setStar(0);
                questionEntityMapper.insertQuestion(questionEntity);
            }
        }
        return CompletableFuture.completedFuture(1);
    }

    //更新项目
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> updateProject(Project project) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lastUpdateDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的最后更新时间
        ProjectEntity projectEntity = new ProjectEntity(project);
        projectEntity.setLastUpdateDate(lastUpdateDate);
        return CompletableFuture.completedFuture(projectEntityMapper.updateProject(projectEntity));
    }

    //将项目的收藏更新为已收藏
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> updateStarOnProject(String id) {
        return CompletableFuture.completedFuture(projectEntityMapper.updateStarOnProject(id));
    }

    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> updateStarOffProject(String id) {
        return CompletableFuture.completedFuture(projectEntityMapper.updateStarOffProject(id));
    }

    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> updateDeletedOnProject(String id) {
        return CompletableFuture.completedFuture(projectEntityMapper.updateDeletedOnProject(id));
    }

    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> updateDeletedOffProject(String id) {
        return CompletableFuture.completedFuture(projectEntityMapper.updateDeletedOffProject(id));
    }

    //删除项目
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> deleteProjectById(String id) {
        return CompletableFuture.completedFuture(projectEntityMapper.deleteProjectById(id));
    }

    //清空回收站
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<Integer> deleteAllProjectRecycled(String userId) {
        return CompletableFuture.completedFuture(projectEntityMapper.deleteAllProjectRecycled(userId));
    }
}
