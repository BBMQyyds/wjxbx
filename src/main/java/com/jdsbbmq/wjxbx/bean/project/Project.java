package com.jdsbbmq.wjxbx.bean.project;

import java.util.Date;

import com.jdsbbmq.wjxbx.dao.entity.ProjectEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "web项目类")
public class Project {
    @Schema(description = "项目id",defaultValue = "1700cd77-cc34-47b2-a453-419fcff8df3b")
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
    public ProjectEntity toProjectEntity() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(this.id);
        projectEntity.setUserId(this.userId);
        projectEntity.setProjectName(this.projectName);
        projectEntity.setProjectContent(this.projectContent);
        projectEntity.setCreatedBy(this.createdBy);
        projectEntity.setCreationDate(this.creationDate);
        projectEntity.setLastUpdatedBy(this.lastUpdatedBy);
        projectEntity.setLastUpdateDate(this.lastUpdateDate);
        return projectEntity;
    }
}
