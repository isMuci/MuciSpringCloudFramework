package com.muci.framework.auth.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Integer roleId;
    // 角色名称
    private String roleName;
    // 角色权限
    private String roleKey;
}
