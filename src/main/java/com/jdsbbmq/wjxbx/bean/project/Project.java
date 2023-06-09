package com.jdsbbmq.wjxbx.bean.project;

import java.util.Date;

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
    @Schema(description = "项目id")
    @Size(min = 36, max = 36)
    private String id;
    @Schema(description = "用户id")
    @Size(min = 36, max = 36)
    private String userId;
    @Schema(description = "项目名称")
    @Size(min = 2, max = 16)
    private String projectName;
    @Schema(description = "项目内容")
    @Size(min = 5, max = 300)
    private String projectContent;
    @Schema(description = "项目开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @Schema(description = "项目创建人")
    private String createdBy;
    @Schema(description = "项目创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @Schema(description = "项目最后更新人")
    private String lastUpdatedBy;
    @Schema(description = "项目最后更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
}
