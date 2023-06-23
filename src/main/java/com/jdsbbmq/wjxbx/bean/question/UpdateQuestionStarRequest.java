package com.jdsbbmq.wjxbx.bean.question;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionStarRequest {
    private String userId;
    private String questionId;
}
