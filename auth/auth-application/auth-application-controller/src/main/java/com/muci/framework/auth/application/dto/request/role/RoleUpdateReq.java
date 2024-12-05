package com.muci.framework.auth.application.dto.request.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色表(SRole)表实体类
 *
 * @author makejava
 * @since 2024-08-21 10:16:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateReq {
    // 角色名称
    private String roleName;
    // 角色权限
    private String roleKey;
    // 状态
    private Integer status;
    // 备注
    // private String remark;
}
