package com.jdsbbmq.wjxbx.dao.entity;

import lombok.*;
import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {
    private String id;
    private String userId;
    private String projectName;
    private String projectContent;
    private Date startTime;
    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;
}
