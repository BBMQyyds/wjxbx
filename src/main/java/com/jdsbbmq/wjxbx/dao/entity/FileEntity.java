package com.jdsbbmq.wjxbx.dao.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.*;
import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    private String id;
    private String fileName;
    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String parentId;
}
