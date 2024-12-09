package com.muci.framework.auth.application.dto.request.user;

import jakarta.validation.constraints.*;
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
public class UserAddReq {
    // 用户账号
    @NotBlank(message = "用户账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$", message = "用户账号必须为4-20位字母、数字或下划线")
    private String username;
    // 用户密码
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 20, message = "密码长度必须在6-20位之间")
    private String password;
    // 用户昵称
    @NotBlank(message = "昵称不能为空")
    @Size(min = 2, max = 30, message = "昵称长度必须在2-30位之间")
    private String nickname;
    // 手机号
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phoneNumber;
    // 邮箱
    @Email(message = "邮箱格式不正确")
    private String email;
    // 出生日期
    @NotNull(message = "出生日期不能为空")
    @Past(message = "出生日期不能是未来日期")
    private LocalDate birth;
    // 性别;0男 1女 2未知
    @NotNull(message = "性别不能为空")
    @Min(value = 0, message = "0男 1女")
    @Max(value = 1, message = "0男 1女")
    private Integer sex;
    // 头像链接
    @URL(message = "头像链接格式不正确")
    private String avatar;
    // 备注
    // private String remark;
}
