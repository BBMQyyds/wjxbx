package com.jdsbbmq.wjxbx.dao.entity;

import com.jdsbbmq.wjxbx.bean.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private String id;
    private String username;
    private String password;
    private Date startTime;
    private Date stopTime;
    private int status;
    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setStartTime(this.startTime);
        user.setStopTime(this.stopTime);
        user.setStatus(this.status);
        user.setCreatedBy(this.createdBy);
        user.setCreationDate(this.creationDate);
        user.setLastUpdatedBy(this.lastUpdatedBy);
        user.setLastUpdateDate(this.lastUpdateDate);
        return user;
    }
}
