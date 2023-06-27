package com.jdsbbmq.wjxbx.bean.question;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {
    private String userId;
    private String questionnaireId;
    private List<Question> questions;
}
