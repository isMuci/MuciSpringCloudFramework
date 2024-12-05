package com.muci.framework.auth.application.dto.request.user;

import com.muci.framework.auth.application.validator.date.range.DateRange;
import com.muci.framework.common.entity.PageInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@DateRange(start = "startBirth", end = "endBirth", message = "出生日期区间无效")
@DateRange(start = "startLogin", end = "endLogin", message = "登录时间区间无效")
@DateRange(start = "startCreate", end = "endCreate", message = "创建时间区间无效")
@DateRange(start = "startUpdate", end = "endUpdate", message = "更新时间区间无效")
public class UserSearchReq extends PageInfo {
    // 用户id列表
    private List<Integer> userIds;
    // 用户账号
    private String username;
    // 用户昵称
    private String nickname;
    // 手机号
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phoneNumber;
    // 邮箱
    @Email(message = "邮箱格式不正确")
    private String email;
    // 起始出生日期
    private LocalDate startBirth;
    // 结束出生日期
    private LocalDate endBirth;
    // 性别;0男 1女 2未知
    @Size(max = 1, message = "0男 1女")
    private Integer sex;
    // 起始登陆时间
    private LocalDate startLogin;
    // 结束登陆时间
    private LocalDate endLogin;
    // 最后登录IP
    private String loginMac;
    // 状态
    @Size(max = 1, message = "0启用 1禁用")
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
    // private String remark;
    // 页码
    private Integer page;
    // 每页数量
    private Integer limit;
}
