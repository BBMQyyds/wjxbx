package com.jdsbbmq.wjxbx.dao.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {
    private String id;
    private String questionId;
    private int sequence;
    private int star;
    private String questionContent;
}
