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
    public Project toProject(){
        Project project = new Project();
        project.setId(this.id);
        project.setUserId(this.userId);
        project.setProjectName(this.projectName);
        project.setProjectContent(this.projectContent);
        project.setCreatedBy(this.createdBy);
        project.setCreationDate(this.creationDate);
        project.setLastUpdatedBy(this.lastUpdatedBy);
        project.setLastUpdateDate(this.lastUpdateDate);
        return project;
    }
}
