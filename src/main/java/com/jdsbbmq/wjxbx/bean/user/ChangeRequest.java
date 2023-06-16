package com.jdsbbmq.wjxbx.bean.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "更改密码请求")
public class ChangeRequest {
    private String username;
    private String originPassword;
    private String newPassword;
    private String lastUpdatedBy;
}
