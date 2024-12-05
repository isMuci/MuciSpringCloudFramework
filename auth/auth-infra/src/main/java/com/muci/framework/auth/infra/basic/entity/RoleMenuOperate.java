package com.muci.framework.auth.infra.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuOperate {
    // 角色ID列表
    private Set<Integer> roleIds;
    // 用户ID列表
    private Set<Integer> menuIds;
    // 删除标志;0正常 1删除
    private Integer isDel;
    // 更新者
    private Integer updateBy;
}
