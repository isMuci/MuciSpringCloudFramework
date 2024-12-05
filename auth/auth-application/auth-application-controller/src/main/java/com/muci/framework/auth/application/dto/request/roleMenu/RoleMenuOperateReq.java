package com.muci.framework.auth.application.dto.request.roleMenu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuOperateReq {
    // 角色ID列表
    private Set<Integer> roleIds;
    // 用户ID列表
    private Set<Integer> menuIds;
}
