package com.jdsbbmq.wjxbx.dao.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {
    private String id;
    private String userId;
    private String questionnaireId;
    private String questionnaireContent;
}
