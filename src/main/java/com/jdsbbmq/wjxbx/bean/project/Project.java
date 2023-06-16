package com.jdsbbmq.wjxbx.bean.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "web项目类")
public class Project {
    @Schema(description = "项目id")
    @Size(min = 36, max = 36)
    private String id;
    @Schema(description = "用户id" ,defaultValue = "53a71d10-f7c4-4d9c-b0d8-a61cf9d3356f")
    @Size(min = 36, max = 36)
    private String userId;
    @Schema(description = "项目名称",defaultValue = "1")
    @Size(min = 2, max = 16)
    private String projectName;
    @Schema(description = "项目内容",defaultValue = "2")
    @Size(min = 5, max = 300)
    private String projectContent;
    @Schema(description = "项目创建人",defaultValue = "3")
    private String createdBy;
    @Schema(description = "项目创建时间",defaultValue = "2021-07-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @Schema(description = "项目最后更新人",defaultValue = "4" )
    private String lastUpdatedBy;
    @Schema(description = "项目最后更新时间",defaultValue = "2021-07-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    public Project(ProjectEntity projectEntity){
        if(projectEntity==null){
            return;
        }else{
            this.id = projectEntity.getId();
            this.userId = projectEntity.getUserId();
            this.projectName = projectEntity.getProjectName();
            this.projectContent = projectEntity.getProjectContent();
            this.createdBy = projectEntity.getCreatedBy();
            this.creationDate = projectEntity.getCreationDate();
            this.lastUpdatedBy = projectEntity.getLastUpdatedBy();
            this.lastUpdateDate = projectEntity.getLastUpdateDate();
        }
    }

    @PostConstruct
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = java.util.UUID.randomUUID().toString(); // 设置默认的id
        this.creationDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的创建时间
        this.lastUpdateDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的最后更新时间
    }
}
