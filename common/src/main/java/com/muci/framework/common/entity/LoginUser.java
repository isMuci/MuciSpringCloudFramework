package com.muci.framework.common.entity;

import java.time.LocalDate;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    // 用户密码
    private String password;
    // token
    private String token;
    // 用户ID
    private Integer userId;
    // 角色
    private List<String> roles;
    // 权限
    private List<String> perms;
    // 用户账号
    private String username;
    // 用户昵称
    private String nickname;
    // 手机号
    private String phoneNumber;
    // 邮箱
    private String email;
    // 出生日期
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate birth;
    // 性别;0男 1女 2未知
    private Integer sex;
    // 头像链接
    private String avatar;

    private String remark;
}
