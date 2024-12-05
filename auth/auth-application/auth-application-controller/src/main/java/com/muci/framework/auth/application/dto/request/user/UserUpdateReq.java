package com.muci.framework.auth.application.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateReq {
    // 用户密码
    @Size(min = 8, max = 20, message = "密码长度必须在6-20位之间")
    private String password;
    // 用户昵称
    @Size(min = 2, max = 30, message = "昵称长度必须在2-30位之间")
    private String nickname;
    // 手机号
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phoneNumber;
    // 邮箱
    @Email(message = "邮箱格式不正确")
    private String email;
    // 出生日期
    @Past(message = "出生日期不能是未来日期")
    private LocalDate birth;
    // 性别;0男 1女 2未知
    @Size(max = 1, message = "0男 1女")
    private Integer sex;
    // 头像链接
    @URL(message = "头像链接格式不正确")
    private String avatar;
    // 状态
    @Size(max = 1, message = "0启用 1禁用")
    private Integer status;
    // 备注
    // private String remark;
}
