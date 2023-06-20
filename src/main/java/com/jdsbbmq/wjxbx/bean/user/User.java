package com.jdsbbmq.wjxbx.bean.user;

import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "web用户类")
public class User {
    @Schema(description = "用户id")
    @Size(min = 36, max = 36)
    private String id;
    @Schema(description = "用户姓名")
    @Size(min = 2, max = 16)
    private String username;
    @Schema(description = "用户密码")
    @Size(min = 6, max = 32)
    private String password;
    @Schema(description = "用户开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @Schema(description = "用户结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopTime;
    @Schema(description = "用户状态")
    @DecimalMin(value = "0")
    @DecimalMax(value = "1")
    private int status;
    @Schema(description = "用户创建者")
    @Size(min = 2, max = 16)
    private String createdBy;
    @Schema(description = "用户创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @Schema(description = "用户更新者")
    @Size(min = 2, max = 16)
    private String lastUpdatedBy;
    @Schema(description = "用户最后更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

    //user->userEntity
    public User(UserEntity userEntity) {
        if (userEntity == null) {
            return;
        } else {
            this.id = userEntity.getId();
            this.username = userEntity.getUsername();
            this.password = userEntity.getPassword();
            this.startTime = userEntity.getStartTime();
            this.stopTime = userEntity.getStopTime();
            this.status = userEntity.getStatus();
            this.createdBy = userEntity.getCreatedBy();
            this.creationDate = userEntity.getCreationDate();
            this.lastUpdatedBy = userEntity.getLastUpdatedBy();
            this.lastUpdateDate = userEntity.getLastUpdateDate();
        }
    }

    @PostConstruct
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = java.util.UUID.randomUUID().toString(); // 设置默认的id
        this.startTime = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的开始时间
        this.creationDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的创建时间
        this.lastUpdateDate = dateFormat.parse(dateFormat.format(new Date())); // 设置默认的最后更新时间
        this.status = 1; // 设置默认的状态
    }
}
