package com.jdsbbmq.wjxbx.bean.answer;

import com.jdsbbmq.wjxbx.bean.question.Question;
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
