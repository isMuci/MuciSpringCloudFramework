package com.muci.framework.auth.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
public class UserRoleBO {
    // 用户ID
    private Integer userId;
    // 角色ID
    private Integer roleId;
    // 删除标志;0正常 1删除
    private Integer isDel;
    // 创建者
    private Integer createBy;
    // 创建时间
    private LocalDateTime createAt;
    // 更新者
    private Integer updateBy;
    // 更新时间
    private LocalDateTime updateAt;
}
