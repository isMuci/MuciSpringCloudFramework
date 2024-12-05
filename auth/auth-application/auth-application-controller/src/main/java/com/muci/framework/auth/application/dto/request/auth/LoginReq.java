package com.muci.framework.auth.application.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {
    // 用户账号
    private String username;
    // 用户密码
    private String password;
    // 登录MAC
    private String loginMac;
}
