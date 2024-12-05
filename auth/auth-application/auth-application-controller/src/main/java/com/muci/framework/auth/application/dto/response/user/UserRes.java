package com.muci.framework.auth.application.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    // 用户ID
    private Integer userId;
    // 用户账号
    private String username;
    // 用户昵称
    private String nickname;
    // 手机号
    private String phoneNumber;
    // 邮箱
    private String email;
    // 出生日期
    private LocalDate birth;
    // 性别;0男 1女 2未知
    private Integer sex;
    // 头像链接
    private String avatar;
    // 最后登陆时间
    private LocalDateTime loginTime;
    // 最后登录IP
    private String loginMac;
    // 状态
    private Integer status;
    // 创建者
    private Integer createBy;
    // 创建时间
    private LocalDateTime createAt;
    // 更新者
    private Integer updateBy;
    // 更新时间
    private LocalDateTime updateAt;
    // 备注
    private String remark;
}
