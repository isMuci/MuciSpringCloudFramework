package com.muci.framework.auth.infra.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 用户角色表(SUserRole)表实体类
 *
 * @author makejava
 * @since 2024-08-21 10:16:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleOperate {

    private Set<Integer> userIds;

    private Set<Integer> roleIds;
    // 删除标志;0正常 1删除
    private Integer isDel;
    // 更新者
    private Integer updateBy;
}
