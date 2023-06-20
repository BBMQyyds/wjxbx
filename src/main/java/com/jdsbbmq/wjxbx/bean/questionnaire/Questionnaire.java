package com.jdsbbmq.wjxbx.bean.questionnaire;

import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "web问卷类")
public class Questionnaire {
    @Schema(description = "问卷id")
    @Size(min = 36, max = 36)
    private String id;
    @Schema(description = "所属项目id")
    @Size(min = 36, max = 36)
    private String projectId;
    @Schema(description = "问卷名")
    @Size(min = 1, max = 12)
    private String questionnaireName;
    @Schema(description = "问卷描述")
    @Size(min = 5, max = 200)
    private String questionnaireDescription;
    @Schema(description = "问卷创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @Schema(description = "问卷发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @Schema(description = "问卷结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @Schema(description = "问卷问题数量")
    private int questionCount;
    @Schema(description = "问卷回答数量")
    private int answerCount;
    @Schema(description = "问卷是否收藏")
    private int star;
    @Schema(description = "问卷是否删除")
    private int deleted;

    public Questionnaire(QuestionnaireEntity questionnaireEntity) {
        if (questionnaireEntity == null) {
            return;
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                this.id = questionnaireEntity.getId();
                this.projectId = questionnaireEntity.getProjectId();
                this.questionnaireName = questionnaireEntity.getQuestionnaireName();
                this.questionnaireDescription = questionnaireEntity.getQuestionnaireDescription();
                this.creationDate = dateFormat.parse(dateFormat.format(questionnaireEntity.getCreationDate()));
                this.startTime = questionnaireEntity.getStartTime();
                this.endTime = questionnaireEntity.getEndTime();
                this.questionCount = questionnaireEntity.getQuestionCount();
                this.answerCount = questionnaireEntity.getAnswerCount();
                this.star = questionnaireEntity.getStar();
                this.deleted = questionnaireEntity.getDeleted();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void init() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.id = java.util.UUID.randomUUID().toString(); // 设置默认的id
            this.creationDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的创建时间
            this.questionCount = 0; // 设置默认的问题数量
            this.answerCount = 0; // 设置默认的回答数量
            this.star = 0; // 设置默认的收藏
            this.deleted = 0; // 设置默认的删除
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
