package com.muci.framework.auth.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleOperate {
    // 用户ID列表
    private Set<Integer> userIds;
    // 角色ID列表
    private Set<Integer> roleIds;
}
