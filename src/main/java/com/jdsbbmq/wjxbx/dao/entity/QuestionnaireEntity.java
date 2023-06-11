package com.jdsbbmq.wjxbx.dao.entity;

import com.jdsbbmq.wjxbx.bean.questionnaire.Questionnaire;
import com.jdsbbmq.wjxbx.service.QuestionnaireService;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireEntity {
    private String id;
    private String projectId;
    private String questionnaireName;
    private String questionnaireDescription;
    private Date creationDate;
    private Date startTime;
    private Date endTime;
    private String status;
    private int Count;

    //复制
    public QuestionnaireEntity(Questionnaire questionnaire){
        this.id = questionnaire.getId();
        this.projectId = questionnaire.getProjectId();
        this.questionnaireName = questionnaire.getQuestionnaireName();
        this.questionnaireDescription = questionnaire.getQuestionnaireDescription();
        this.creationDate = questionnaire.getCreationDate();
        this.startTime = questionnaire.getStartTime();
        this.endTime = questionnaire.getEndTime();
        this.status = questionnaire.getStatus();
        this.Count = questionnaire.getCount();
    }
}
