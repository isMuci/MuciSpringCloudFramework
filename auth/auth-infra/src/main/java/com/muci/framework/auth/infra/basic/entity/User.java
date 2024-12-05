package com.muci.framework.auth.infra.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("s_user")
public class User extends Model<User> {
    // 用户ID
    @TableId(type = IdType.AUTO)
    private Integer userId;
    // 用户账号
    private String username;
    // 用户密码
    private String password;
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
    // 状态
    private Integer status;
    // 删除标志;0正常 1删除
    private Integer isDel;
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
