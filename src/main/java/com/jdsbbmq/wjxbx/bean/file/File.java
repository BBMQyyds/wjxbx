package com.jdsbbmq.wjxbx.bean.file;

import com.jdsbbmq.wjxbx.dao.entity.FileEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "web文件夹类")
public class File {
    @Schema(description = "文件夹id")
    @Size(min = 36, max = 36)
    private String id;
    @Schema(description = "文件夹名称")
    @Size(min = 1, max = 16)
    private String fileName;
    @Schema(description = "文件夹创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @Schema(description = "文件夹创建人")
    @Size(min = 1, max = 16)
    private String createdBy;
    @Schema(description = "文件夹最后被更新日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    @Schema(description = "文件夹父Id")
    @Size(min = 36, max = 36)
    private String parentId;

    public File(FileEntity fileEntity) {
        this.id = fileEntity.getId();
        this.fileName = fileEntity.getFileName();
        this.creationDate = fileEntity.getCreationDate();
        this.createdBy = fileEntity.getCreatedBy();
        this.lastUpdateDate = fileEntity.getLastUpdateDate();
        this.parentId = fileEntity.getParentId();
    }

    @PostConstruct
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = java.util.UUID.randomUUID().toString(); // 设置默认的id
        this.creationDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的创建时间
        this.lastUpdateDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的最后更新时间
    }
}
