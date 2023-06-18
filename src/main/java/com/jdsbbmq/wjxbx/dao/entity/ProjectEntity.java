package com.jdsbbmq.wjxbx.dao.entity;

import com.jdsbbmq.wjxbx.bean.project.Project;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userId;
    private String projectName;
    private String projectContent;
    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;
    private int star;
    private int questionnaireCount;
    public ProjectEntity(Project project){
        if(project==null){
            return;
        }else{
            this.id = project.getId();
            this.userId = project.getUserId();
            this.projectName = project.getProjectName();
            this.projectContent = project.getProjectContent();
            this.createdBy = project.getCreatedBy();
            this.creationDate = project.getCreationDate();
            this.lastUpdatedBy = project.getLastUpdatedBy();
            this.lastUpdateDate = project.getLastUpdateDate();
            this.star = project.getStar();
            this.questionnaireCount = project.getQuestionnaireCount();
        }
    }
}
