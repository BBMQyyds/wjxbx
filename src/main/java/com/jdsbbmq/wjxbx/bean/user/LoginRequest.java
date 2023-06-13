package com.jdsbbmq.wjxbx.bean.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "登录请求")
public class LoginRequest {
    String username;
    String password;
}
