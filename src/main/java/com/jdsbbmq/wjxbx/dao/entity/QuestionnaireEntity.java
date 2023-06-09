package com.jdsbbmq.wjxbx.dao.entity;

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
}
