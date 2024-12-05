package com.muci.framework.auth.infra.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("s_user_login")
public class UserLogin extends Model<UserLogin> {
    // 用户ID
    private Integer userId;
    // 登录MAC
    private String loginMac;
    // 登录时间
    private LocalDate loginTime;

}
