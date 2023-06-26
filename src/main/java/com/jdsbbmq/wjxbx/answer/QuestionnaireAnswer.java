package com.jdsbbmq.wjxbx.answer;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireAnswer {
    private String id;
    private String userId;
    private String questionnaireId;
    private String questionnaireContent;
    private String answerContent;
}
