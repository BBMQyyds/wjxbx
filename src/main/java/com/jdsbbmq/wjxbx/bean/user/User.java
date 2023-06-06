package com.jdsbbmq.wjxbx.bean.user;

import com.jdsbbmq.wjxbx.dao.entity.UserEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//用户
public class User {

    @Size(min = 36, max = 36)
    private String id;

    @Size(min = 2, max = 16)
    private String username;

    @Size(min = 6, max = 32)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopTime;

    @DecimalMin(value = "0")
    @DecimalMax(value = "1")
    private int status;

    @Size(min = 2, max = 16)
    private String createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @Size(min = 2, max = 16)
    private String lastUpdatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

    //user->userEntity
    public UserEntity toUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(this.id);
        userEntity.setUsername(this.username);
        userEntity.setPassword(this.password);
        userEntity.setStartTime(this.startTime);
        userEntity.setStopTime(this.stopTime);
        userEntity.setStatus(this.status);
        userEntity.setCreatedBy(this.createdBy);
        userEntity.setCreationDate(this.creationDate);
        userEntity.setLastUpdatedBy(this.lastUpdatedBy);
        userEntity.setLastUpdateDate(this.lastUpdateDate);
        return userEntity;
    }


}
