package com.muci.framework.auth.application.dto.response.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRes {
    // 角色ID
    private Integer roleId;
    // 角色名称
    private String roleName;
    // 角色权限
    private String roleKey;
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
