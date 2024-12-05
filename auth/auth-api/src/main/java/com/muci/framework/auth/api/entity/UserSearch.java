package com.muci.framework.auth.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearch {
    // 用户id列表
    private List<Integer> userIds;
    // 用户账号
    private String username;
    // 用户昵称
    private String nickname;
    // 手机号
    private String phoneNumber;
    // 邮箱
    private String email;
    // 起始出生日期
    private LocalDate startBirth;
    // 结束出生日期
    private LocalDate endBirth;
    // 性别;0男 1女 2未知
    private Integer sex;
    // 起始登陆时间
    private LocalDate startLogin;
    // 结束登陆时间
    private LocalDate endLogin;
    // 头像链接
    private String avatar;
    // 最后登录IP
    private String loginMac;
    // 状态
    private Integer status;
    // 创建者
    private Integer createBy;
    // 起始创建时间
    private LocalDate startCreate;
    // 结束创建时间
    private LocalDate endCreate;
    // 更新者
    private Integer updateBy;
    // 起始更新时间
    private LocalDate startUpdate;
    // 结束更新时间
    private LocalDate endUpdate;
    // 备注
    private String remark;
    // 页码
    private Integer page;
    // 每页数量
    private Integer limit;
}
