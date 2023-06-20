package com.jdsbbmq.wjxbx.dao.entity;

import com.jdsbbmq.wjxbx.bean.user.User;
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
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public UserEntity(User user) {
        if (user == null) {
            return;
        } else {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.startTime = user.getStartTime();
            this.stopTime = user.getStopTime();
            this.status = user.getStatus();
            this.createdBy = user.getCreatedBy();
            this.creationDate = user.getCreationDate();
            this.lastUpdatedBy = user.getLastUpdatedBy();
            this.lastUpdateDate = user.getLastUpdateDate();
        }
    }
}
