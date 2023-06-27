package com.jdsbbmq.wjxbx.dao.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {
    private String id;
    private String userId;
    private String username;
    private String questionnaireId;
    private String questionnaireName;
    private String questionnaireContent;
    private Date creationDate;
}
