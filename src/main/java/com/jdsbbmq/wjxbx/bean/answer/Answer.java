package com.jdsbbmq.wjxbx.bean.answer;

import com.jdsbbmq.wjxbx.dao.entity.AnswerEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Answer {
    @Schema(description = "答案id")
    @Size(min = 36, max = 36)
    private String id;
    @Schema(description = "用户id")
    @Size(min = 36, max = 36)
    private String userId;
    @Schema(description = "用户名")
    @Size(min = 2, max = 16)
    private String username;
    @Schema(description = "问卷id")
    @Size(min = 36, max = 36)
    private String questionnaireId;
    @Schema(description = "问卷名")
    @Size(min = 1, max = 12)
    private String questionnaireName;
    @Schema(description = "问卷内容")
    @Size(min = 5, max = 200)
    private String questionnaireContent;
    @Schema(description = "答案创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public Answer(AnswerEntity answerEntity) {
        if (answerEntity == null) {
            return;
        } else {
            this.id = answerEntity.getId();
            this.userId = answerEntity.getUserId();
            this.username = answerEntity.getUsername();
            this.questionnaireId = answerEntity.getQuestionnaireId();
            this.questionnaireName = answerEntity.getQuestionnaireName();
            this.questionnaireContent = answerEntity.getQuestionnaireContent();
            this.createDate = answerEntity.getCreateDate();
        }
    }
}
