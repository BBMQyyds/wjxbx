package com.jdsbbmq.wjxbx.bean.questionnaire;

import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
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
    @Schema(description = "问卷状态")
    private String status;
    @Schema(description = "问卷答卷数量")
    private int Count;
    public Questionnaire(QuestionnaireEntity questionnaireEntity){
        if(questionnaireEntity==null){
            return;
        }else{
            this.id = questionnaireEntity.getId();
            this.projectId = questionnaireEntity.getProjectId();
            this.questionnaireName = questionnaireEntity.getQuestionnaireName();
            this.questionnaireDescription = questionnaireEntity.getQuestionnaireDescription();
            this.creationDate = questionnaireEntity.getCreationDate();
            this.startTime = questionnaireEntity.getStartTime();
            this.endTime = questionnaireEntity.getEndTime();
            this.status = questionnaireEntity.getStatus();
            this.Count = questionnaireEntity.getCount();
        }

    }
}
