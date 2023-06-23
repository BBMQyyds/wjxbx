package com.jdsbbmq.wjxbx.dao.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {
    private String questionnaireId;
    private int sequence;
    private String questionContent;
}
